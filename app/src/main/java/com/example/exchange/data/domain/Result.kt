package com.example.exchange.data.domain

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
sealed class Result<out T, out R> {
    class Success<out T>(val data: T) : Result<T, Nothing>()
    class Failure<out R : Exception>(val error: R) : Result<Nothing, R>()

    sealed class State : Result<Nothing, Nothing>() {
        class Loading : State()
        class Loaded : State()
    }

    fun handleResult(
        stateBlock: (State) -> Unit = {},
        failureBlock: (R) -> Unit = {},
        successBlock: (T) -> Unit = {}) {

        when (this) {
            is Success -> successBlock(data)
            is Failure -> failureBlock(error)
            is State -> stateBlock(this)
        }
    }

    suspend fun handleSuspendedResult(
        stateBlock: suspend (State) -> Unit = {},
        failureBlock: suspend (R) -> Unit = {},
        successBlock: suspend (T) -> Unit = {}) {

        when (this) {
            is Success -> successBlock(data)
            is Failure -> failureBlock(error)
            is State -> stateBlock(this)
        }
    }

    suspend fun handleSuccess(successBlock: suspend (T) -> Unit) {
        if (this is Success) {
            successBlock(data)
        }
    }

    suspend fun handleFailure(failureBlock: suspend (R) -> Unit) {
        if (this is Failure) {
            failureBlock(error)
        }
    }
}