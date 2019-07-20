package com.example.exchange

import com.example.exchange.data.domain.GetCurrenciesUseCase
import com.example.exchange.data.model.remote.retrofit.currency.RemoteCurrencyDataSource
import com.example.exchange.ui.CurrencyViewModelFactory

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */

fun provideCurrencyViewModelFactory(currencyCode: String) =
    CurrencyViewModelFactory(currencyCode = currencyCode, getCurrenciesUseCase = provideCurrencyUseCase())

fun provideCurrencyUseCase() =
    GetCurrenciesUseCase(remoteCurrencyDataSource = RemoteCurrencyDataSource())