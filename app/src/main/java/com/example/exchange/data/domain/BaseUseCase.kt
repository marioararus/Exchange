package com.example.exchange.data.domain

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.CoroutineContext

/**
 *
 * Created by Marioara Rus on 2019-07-03
 */

abstract class BaseUseCase<T> : CoroutineScope {

    // region Members
    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    protected val backgroundDispatcher = Dispatchers.Default
    protected val resultChannel = Channel<Result<T, Exception>>()

    val receiveChannel: ReceiveChannel<Result<T, Exception>> = resultChannel

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    protected fun <T> startAsync(block: suspend () -> T): Deferred<T> = async(parentJob) {
        block()
    }

    /**
     * Should be called when use-case owner is destroyed
     * This will ensure coroutine is cancelled if it's still running some tasks
     */
    fun clear() {
        resultChannel.close()
        parentJob.cancel()
    }

    protected open suspend fun handleGeneralFailure(exception: Exception) {
        resultChannel.send(Result.State.Loaded())
    }
}

class None : Any()