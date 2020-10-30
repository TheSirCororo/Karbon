package com.karbonpowered.protocol.java.handler.handshake

import com.karbonpowered.network.message.MessageHandler
import com.karbonpowered.protocol.java.MinecraftProtocol
import com.karbonpowered.protocol.java.MinecraftSession
import com.karbonpowered.protocol.java.ProtocolState
import com.karbonpowered.protocol.java.c2s.handshake.HandshakeC2SPacket
import java.net.InetSocketAddress

class HandshakeHandler(
    val queryProtocol: MinecraftProtocol,
    val loginProtocol: MinecraftProtocol
) : MessageHandler<HandshakeC2SPacket, MinecraftSession> {
    override fun handle(message: HandshakeC2SPacket, session: MinecraftSession) {
        session.protocolState = message.intendedState
        when (message.intendedState) {
            ProtocolState.QUERY -> session.protocol = queryProtocol
            ProtocolState.LOGIN -> session.protocol = loginProtocol
            else -> session.disconnect()
        }

        session.protocolVersion = message.protocolVersion
        session.virtualHost = InetSocketAddress.createUnresolved(message.address, message.port)
    }
}