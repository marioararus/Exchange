package com.example.exchange.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exchange.data.domain.GetCurrenciesUseCase
import kotlinx.coroutines.ObsoleteCoroutinesApi
import java.lang.IllegalArgumentException

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */
class CurrencyViewModelFactory(
    private val currencyCode: String,
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModelProvider.Factory {

    @ObsoleteCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            return CurrencyViewModel(currencyCode, getCurrenciesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}