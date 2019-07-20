package com.example.exchange.data.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Created by Marioara Rus on 2019-07-18
 */
abstract class UseCaseWithParams<in Params, T> : CoroutineScope, BaseUseCase<T>() {

    protected abstract suspend fun run(params: Params)

    /**
     * By overriding invoke, we allow use cases to be called as "invoking"
     */
    operator fun invoke(params: Params) {
        launch {
            withContext(backgroundDispatcher) {
                run(params)
            }
        }
    }
}