package com.example.exchange.data.model.remote.retrofit.currency.entity

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
data class RemoteCurrency(
    var base: String?,
    var date: String?,
    var rates: RemoteRate?
)