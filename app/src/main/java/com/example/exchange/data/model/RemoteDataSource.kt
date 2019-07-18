package com.example.exchange.data.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
abstract class RemoteDataSource {
    protected val retrofit: Retrofit
    protected val URL_PATH = "https://revolut.duckdns.org"

    private val clientBuilder = OkHttpClient.Builder()

    private val loggingInterceptor = HttpLoggingInterceptor()

    init {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        clientBuilder.addInterceptor(loggingInterceptor)
            .readTimeout(1000, TimeUnit.SECONDS)
            .connectTimeout(1000, TimeUnit.SECONDS)

        retrofit = Retrofit.Builder()
            .baseUrl(URL_PATH)
            .client(clientBuilder.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}