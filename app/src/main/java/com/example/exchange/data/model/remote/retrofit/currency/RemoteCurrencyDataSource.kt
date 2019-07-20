package com.example.exchange.data.model.remote.retrofit.currency

import android.util.Log
import com.example.exchange.data.domain.Result
import com.example.exchange.data.entity.Currency
import com.example.exchange.data.model.RemoteDataSource
import com.example.exchange.data.model.remote.retrofit.CurrencyService
import com.example.exchange.data.model.remote.retrofit.exception.CurrencyIORemoteException
import com.example.exchange.data.model.remote.toCurrency

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
class RemoteCurrencyDataSource : RemoteDataSource() {
    private val TAG = RemoteCurrencyDataSource::class.java.simpleName
    private val currencyService: CurrencyService = retrofit.create(CurrencyService::class.java)

    suspend fun getCurrency(currency: String): Result<Currency, Exception> {
        return try {
            val remoteCurrency = currencyService.getCurrencies(currency).await()

            if (remoteCurrency != null) {
                remoteCurrency.toCurrency()
                Result.Success(data = remoteCurrency.toCurrency())
            } else {
                Result.Failure(CurrencyIORemoteException.NoCurrencyFoundIOException(Exception("Currency is null")))
            }

        } catch (exception: Exception) {
            Log.e(TAG, "getCurrency ${exception.message}")
            Result.Failure(CurrencyIORemoteException.NoCurrencyFoundIOException(exception))
        }
    }
}