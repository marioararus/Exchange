package com.example.exchange.data.model.remote.retrofit.exception

import android.util.Log
import java.io.IOException
import java.lang.Exception

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
sealed class CurrencyIORemoteException(override val message: String):IOException() {
    class NoCurrencyFoundIOException(exception: Exception):
            CurrencyIORemoteException(message = "No currency found in the remote database. $exception"){
        companion object{
            private val TAG = NoCurrencyFoundIOException::class.java.simpleName
        }

        init {
            Log.e(TAG, message, this)
        }
    }
}