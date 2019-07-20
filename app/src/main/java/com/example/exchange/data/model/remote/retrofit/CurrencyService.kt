package com.example.exchange.data.model.remote.retrofit

import com.example.exchange.data.model.remote.retrofit.currency.entity.RemoteCurrency
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
interface CurrencyService {
    @GET("/latest?")
    fun getCurrencies(@Query("base") currency: String): Deferred<RemoteCurrency>
}