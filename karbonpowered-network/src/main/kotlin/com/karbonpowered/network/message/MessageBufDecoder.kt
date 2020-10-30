package com.karbonpowered.network.message

fun interface MessageBufDecoder<T : Any> {
    fun decode(buffer: MessageBuf): T
}