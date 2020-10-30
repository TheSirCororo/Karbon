package com.karbonpowered.commons.io.async

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

fun <T> Deferred<T>.syncAwait(): T = runBlocking { await() }
operator fun ExecutorService.invoke(callback: () -> Unit) {
    this.execute(callback)
}

@OptIn(ObsoleteCoroutinesApi::class)
private val mainDispatcher by lazy { newSingleThreadContext("mainDispatcher") }

@OptIn(ObsoleteCoroutinesApi::class)
internal val workerContext by lazy { newSingleThreadContext("worker") }

fun asyncEntryPoint(callback: suspend CoroutineScope.() -> Unit) =
    runBlocking(mainDispatcher) { callback() }

fun <T> withBlockingTimeout(timeMillis: Long, block: suspend CoroutineScope.() -> T): T =
    runBlocking { withTimeout(timeMillis, block) }

fun <T> withBlockingTimeout(duration: Long, unit: TimeUnit, block: suspend CoroutineScope.() -> T): T =
    withBlockingTimeout(unit.toMillis(duration), block)

@ExperimentalTime
fun <T> withBlockingTimeout(duration: Duration, block: suspend CoroutineScope.() -> T): T =
    runBlocking { withTimeout(duration, block) }

fun <T> withBlockingTimeoutOrNull(timeMillis: Long, block: suspend CoroutineScope.() -> T): T? =
    runBlocking { withTimeoutOrNull(timeMillis, block) }

fun <T> withBlockingTimeoutOrNull(duration: Long, unit: TimeUnit, block: suspend CoroutineScope.() -> T): T? =
    withBlockingTimeoutOrNull(unit.toMillis(duration), block)

@ExperimentalTime
fun <T> withBlockingTimeoutOrNull(duration: Duration, block: suspend CoroutineScope.() -> T): T? =
    runBlocking { withTimeoutOrNull(duration, block) }

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.launch(callback: suspend CoroutineScope.() -> Unit) = _launch(CoroutineStart.UNDISPATCHED, callback)

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.launchImmediately(callback: suspend CoroutineScope.() -> Unit) =
    _launch(CoroutineStart.UNDISPATCHED, callback)

fun CoroutineScope.launchAsap(callback: suspend CoroutineScope.() -> Unit) = _launch(CoroutineStart.DEFAULT, callback)

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> CoroutineScope.async(callback: suspend CoroutineScope.() -> T) = _async(CoroutineStart.UNDISPATCHED, callback)

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> CoroutineScope.asyncImmediately(callback: suspend CoroutineScope.() -> T) =
    _async(CoroutineStart.UNDISPATCHED, callback)

fun <T> CoroutineScope.asyncAsap(callback: suspend CoroutineScope.() -> T) = _async(CoroutineStart.DEFAULT, callback)

fun launch(context: CoroutineContext, callback: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(context).launchImmediately(callback)

fun launchImmediately(context: CoroutineContext, callback: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(context).launchImmediately(callback)

fun launchAsap(context: CoroutineContext, callback: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(context).launchAsap(callback)

fun <T> async(context: CoroutineContext, callback: suspend CoroutineScope.() -> T) =
    CoroutineScope(context).asyncImmediately(callback)

fun <T> asyncImmediately(context: CoroutineContext, callback: suspend CoroutineScope.() -> T) =
    CoroutineScope(context).asyncImmediately(callback)

fun <T> asyncAsap(context: CoroutineContext, callback: suspend CoroutineScope.() -> T) =
    CoroutineScope(context).asyncAsap(callback)

fun <T> ReceiveChannel<T>.receiveBlocking(): T = runBlocking { receive() }

private fun CoroutineScope._launch(start: CoroutineStart, callback: suspend CoroutineScope.() -> Unit): Job =
    launch(coroutineContext, start = start) {
        try {
            callback()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }

private fun <T> CoroutineScope._async(start: CoroutineStart, callback: suspend CoroutineScope.() -> T): Deferred<T> =
    async(coroutineContext, start = start) {
        try {
            callback()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            e.printStackTrace()
            throw e
        }
    }