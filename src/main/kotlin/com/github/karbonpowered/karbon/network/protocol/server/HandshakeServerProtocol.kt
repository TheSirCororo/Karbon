package com.github.karbonpowered.karbon.network.protocol.server

import com.github.karbonpowered.protocol.java.ProtocolState
import com.github.karbonpowered.protocol.java.c2s.handshake.HandshakeC2SPacket
import com.github.karbonpowered.protocol.java.protocol.HandshakeProtocol

object HandshakeServerProtocol : HandshakeProtocol(true) {
    init {
        registerHandler(HandshakeC2SPacket::class.java) { session, packet ->
            session.protocolState = packet.intendedState
            session.protocol = when (packet.intendedState) {
                ProtocolState.HANDSHAKE -> throw RuntimeException("Handshake after handshake!?")
                ProtocolState.QUERY -> QueryServerProtocol
                ProtocolState.LOGIN -> LoginServerProtocol
                ProtocolState.GAME -> GameServerProtocol
            }
        }
    }
}