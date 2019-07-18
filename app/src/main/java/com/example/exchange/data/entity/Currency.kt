package com.example.exchange.data.entity

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
data class Currency(
    var code: String?,
    var date: String?,
    var name: String?,
    var currencyRates: List<CurrencyRate>,
    var iconResId: Int?
)