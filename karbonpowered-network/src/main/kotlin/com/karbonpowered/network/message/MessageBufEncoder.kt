package com.karbonpowered.network.message

fun interface MessageBufEncoder<T : Any> {
    fun encode(buffer: MessageBuf, message: T): MessageBuf
}