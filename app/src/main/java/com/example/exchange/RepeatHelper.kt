package com.example.exchange

import android.os.Handler

/**
 *
 * Created by Marioara Rus on 2019-07-19
 */
object RepeatHelper {

    private lateinit var runnable: Runnable
    private val handler = Handler()

    fun repeatDelayed(delay: Long, todo: () -> Unit) {
        runnable = object : Runnable {
            override fun run() {
                todo()
                handler.postDelayed(this, delay)
            }
        }

        handler.postDelayed(runnable, delay)
    }

    fun stopDelayed() {
        handler.removeCallbacks(runnable)
    }
}