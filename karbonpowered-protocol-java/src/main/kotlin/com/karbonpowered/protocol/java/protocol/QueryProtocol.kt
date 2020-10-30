package com.karbonpowered.protocol.java.protocol

import com.karbonpowered.protocol.java.MinecraftProtocol
import com.karbonpowered.protocol.java.c2s.query.QueryPingC2SPacket
import com.karbonpowered.protocol.java.c2s.query.QueryRequestC2SPacket
import com.karbonpowered.protocol.java.s2c.query.QueryPongS2CPacket
import com.karbonpowered.protocol.java.s2c.query.QueryResponseS2CPacket

open class QueryProtocol(isServerSide: Boolean) : MinecraftProtocol("query", isServerSide, 4) {
    init {
        registerPacket(true, QueryRequestC2SPacket::class, QueryRequestC2SPacket.Codec::class)
        registerPacket(true, QueryPingC2SPacket::class, QueryPingC2SPacket.Codec::class)

        registerPacket(false, QueryResponseS2CPacket::class, QueryResponseS2CPacket.Codec::class)
        registerPacket(false, QueryPongS2CPacket::class, QueryPongS2CPacket.Codec::class)
    }
}