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
 * this method converts a [RemoteRate] to [Rate]
 * @return a converted rate
 */
fun RemoteRate.toRates() =
    Rate(
        AUD = AUD,
        BGN = BGN,
        BRL = BRL,
        CAD = CAD,
        CHF = CHF,
        CNY = CNY,
        CZK = CZK,
        DKK = DKK,
        GBP = GBP,
        HKD = HKD,
        HRK = HRK,
        HUF = HUF,
        IDR = IDR,
        ILS = ILS,
        INR = INR,
        ISK = ISK,
        JPY = JPY,
        KRW = KRW,
        MXN = MXN,
        MYR = MYR,
        NOK = NOK,
        NZD = NZD,
        PHP = PHP,
        PLN = PLN,
        RON = RON,
        RUB = RUB,
        SEK = SEK,
        SGD = SGD,
        THB = THB,
        TRY = TRY,
        ZAR = ZAR,
        EUR = EUR
    )

/**
 * this method converts a [RemoteCurrency] to [Currency]
 * @return a converted currency
 */
fun RemoteCurrency.toCurrency(): Currency {
    val currency = base?.let { Currencies.getCurrency(it) }

    return Currency(base, date, currency?.currencyName, rates?.toCurrencyRate().orEmpty(), currency?.flagResId)
}

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

/**
 * this method converts a [Rate] to [RemoteRate]
 * @return a converted rate
 */
fun Rate.toRemoteRates() =
    RemoteRate(
        AUD = AUD,
        BGN = BGN,
        BRL = BRL,
        CAD = CAD,
        CHF = CHF,
        CNY = CNY,
        CZK = CZK,
        DKK = DKK,
        GBP = GBP,
        HKD = HKD,
        HRK = HRK,
        HUF = HUF,
        IDR = IDR,
        ILS = ILS,
        INR = INR,
        ISK = ISK,
        JPY = JPY,
        KRW = KRW,
        MXN = MXN,
        MYR = MYR,
        NOK = NOK,
        NZD = NZD,
        PHP = PHP,
        PLN = PLN,
        RON = RON,
        RUB = RUB,
        SEK = SEK,
        SGD = SGD,
        THB = THB,
        TRY = TRY,
        ZAR = ZAR,
        EUR = EUR
    )

/**
 * this method converts a list of [RemoteCurrency] to a list of [Currency]
 * @return a converted currency
 */
fun List<RemoteCurrency>.toCurrencies() =
    map { it.toCurrency() }