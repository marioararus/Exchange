package com.example.exchange.data.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */
abstract class UseCaseWithoutParams<T> : CoroutineScope, BaseUseCase<T>() {

    protected abstract suspend fun run()

    /**
     * By overriding invoke, we allow use cases to be called as "invoking"
     */
    operator fun invoke() {
        launch {
            withContext(backgroundDispatcher) {
                run()
            }
        }
    }
}