package com.example.exchange.data.domain

import com.example.exchange.data.entity.Currency
import com.example.exchange.data.model.remote.retrofit.currency.RemoteCurrencyDataSource

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
class GetCurrenciesUseCase(
    private val remoteCurrencyDataSource: RemoteCurrencyDataSource
) : UseCaseWithParams<String, Currency>() {

    override suspend fun run(currency: String) {
        resultChannel.send(Result.State.Loading())

        val remoteCurrencyResult = remoteCurrencyDataSource.getCurrency(currency)

        resultChannel.send(remoteCurrencyResult)

        resultChannel.send(Result.State.Loaded())
    }

}