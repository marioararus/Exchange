package com.example.exchange.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exchange.BaseViewModel
import com.example.exchange.RepeatHelper
import com.example.exchange.data.domain.GetCurrenciesUseCase
import com.example.exchange.data.domain.Result
import com.example.exchange.data.entity.Currency
import com.example.exchange.data.entity.CurrencyRate

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */
class CurrencyViewModel(
    var currencyCode: String,
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : BaseViewModel() {

    private val _currency = MutableLiveData<Currency>()
    val currency: LiveData<Currency>
        get() = _currency

    private val _currencyRates = MutableLiveData<List<CurrencyRate>>()
    val currencyRates: LiveData<List<CurrencyRate>>
        get() = _currencyRates

    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String>
        get() = _showError

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    init {
        getCurrenciesUseCase.listen(
            ::handleState, ::handleFailure, ::handleCurrencySuccess
        )
    }

    private fun handleFailure(exception: Exception) {
        _showError.value = exception.message
    }

    private fun handleState(state: Result.State) {
        when (state) {
            is Result.State.Loading -> _showLoading.value = true
            is Result.State.Loaded -> _showLoading.value = false
        }
    }

    private fun handleCurrencySuccess(data: Currency) {
        _currency.value = data
        val rate = data.currencyRates.find { it.code == data.code }
        val rates = data.currencyRates.toMutableList()
        rates.remove(rate)
        _currencyRates.value = rates
    }

    fun getCurrencies() {
        RepeatHelper.repeatDelayed(1000) {
            getCurrenciesUseCase(currencyCode)
        }
    }
}