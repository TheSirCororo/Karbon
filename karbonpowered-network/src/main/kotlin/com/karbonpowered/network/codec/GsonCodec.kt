package com.karbonpowered.network.codec

import com.google.gson.Gson
import com.karbonpowered.network.message.MessageBuf

class GsonCodec<M : Any>(
    val gson: Gson,
    val type: Class<M>
) : Codec<M> {
    override fun decode(buffer: MessageBuf): M {
        val json = buffer.readString()
        return gson.fromJson(json, type)
    }

    override fun encode(buffer: MessageBuf, message: M): MessageBuf {
        val json = gson.toJson(message, type)
        buffer.writeString(json)
        return buffer
    }
}