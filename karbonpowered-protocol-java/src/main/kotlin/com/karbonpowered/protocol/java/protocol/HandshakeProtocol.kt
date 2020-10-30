package com.karbonpowered.protocol.java.protocol

import com.karbonpowered.protocol.java.MinecraftProtocol
import com.karbonpowered.protocol.java.c2s.handshake.HandshakeC2SPacket
import com.karbonpowered.protocol.java.handler.handshake.HandshakeHandler

open class HandshakeProtocol(isServer: Boolean) : MinecraftProtocol("handshake", isServer, 1) {
    init {
        registerPacket(true, HandshakeC2SPacket::class.java, HandshakeC2SPacket.Codec::class.java)

        registerHandler(HandshakeC2SPacket::class, HandshakeHandler(QueryProtocol(true), LoginProtocol(true)))
    }
}