package com.example.exchange

import androidx.lifecycle.ViewModel
import com.example.exchange.data.domain.BaseUseCase
import com.example.exchange.data.domain.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

/**
 *
 * Created by Marioara Rus on 2019-07-17
 */
@ObsoleteCoroutinesApi
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    protected fun <D> BaseUseCase<D>.listen(stateBlock:(Result.State) -> Unit,
                                            failureBlock: (Exception) -> Unit = {},
                                            successBlock: (D) -> Unit = {}){
        launch {
            receiveChannel.consumeEach {
                it.handleResult(stateBlock, failureBlock, successBlock)
            }
        }
    }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}