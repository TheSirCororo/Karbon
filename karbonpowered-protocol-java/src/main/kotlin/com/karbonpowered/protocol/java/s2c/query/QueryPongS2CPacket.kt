package com.karbonpowered.protocol.java.s2c.query

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class QueryPongS2CPacket(
    val startTime: Long
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<QueryPongS2CPacket> {
        override fun decode(buffer: MessageBuf): QueryPongS2CPacket {
            val startIme = buffer.readLong()
            return QueryPongS2CPacket(startIme)
        }

        override fun encode(buffer: MessageBuf, message: QueryPongS2CPacket): MessageBuf {
            buffer.writeLong(message.startTime)
            return buffer
        }
    }
}