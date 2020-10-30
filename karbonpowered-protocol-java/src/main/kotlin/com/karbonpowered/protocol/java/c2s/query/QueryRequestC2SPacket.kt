package com.karbonpowered.protocol.java.c2s.query

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

class QueryRequestC2SPacket : MinecraftPacket() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String = "QueryRequestC2SPacket()"

    object Codec : com.karbonpowered.network.codec.Codec<QueryRequestC2SPacket> {
        override fun decode(buffer: MessageBuf): QueryRequestC2SPacket = QueryRequestC2SPacket()

        override fun encode(buffer: MessageBuf, message: QueryRequestC2SPacket): MessageBuf = buffer
    }
}