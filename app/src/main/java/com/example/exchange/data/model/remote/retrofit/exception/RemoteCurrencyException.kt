package com.example.exchange.data.model.remote.retrofit.exception

import android.util.Log

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
sealed class RemoteCurrencyException(override val message: String) : Exception() {

    class NoCurrencyFoundException() :
        RemoteCurrencyException(message = "Currency could not be found.") {
        companion object {
            private val TAG = NoCurrencyFoundException::class.java.simpleName
        }

        init {
            Log.e(TAG, message, this)
        }
    }
}