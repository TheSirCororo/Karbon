package com.karbonpowered.protocol.java.c2s.query

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class QueryPingC2SPacket(
    val startTime: Long
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<QueryPingC2SPacket> {
        override fun decode(buffer: MessageBuf): QueryPingC2SPacket {
            val startIme = buffer.readLong()
            return QueryPingC2SPacket(startIme)
        }

        override fun encode(buffer: MessageBuf, message: QueryPingC2SPacket): MessageBuf {
            buffer.writeLong(message.startTime)
            return buffer
        }
    }
}