package com.example.exchange.data.model.remote

import com.example.exchange.data.entity.Currencies
import com.example.exchange.data.entity.Currency
import com.example.exchange.data.entity.CurrencyRate
import com.example.exchange.data.entity.Rate
import com.example.exchange.data.model.remote.retrofit.currency.entity.RemoteCurrency
import com.example.exchange.data.model.remote.retrofit.currency.entity.RemoteRate

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */

/**
 * this method converts a [RemoteCurrency] to [Currency]
 * @return a converted currency
 */
fun RemoteCurrency.toCurrency(): Currency {
    val currency = base?.let { Currencies.getCurrency(it) }

    return Currency(base, date, currency?.currencyName, rates?.toCurrencyRate().orEmpty(), currency?.flagResId)
}

/**
 * this method converts a [RemoteRate] to [CurrencyRate]
 * @return a converted CurrencyRate
 */
fun RemoteRate.toCurrencyRate(): List<CurrencyRate> {
    val currencyRates = mutableListOf<CurrencyRate>()
    RemoteRate::class.java.declaredFields.forEach {
        val currency = Currencies.getCurrency(it.name)
        it.isAccessible = true
        currencyRates.add(
            CurrencyRate(
                currency?.currencyCode,
                currency?.currencyName,
                it.getFloat(this),
                currency?.flagResId
            )
        )
    }
    return currencyRates
}