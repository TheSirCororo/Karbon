package com.github.karbonpowered.karbon

import com.github.karbonpowered.karbon.network.protocol.server.HandshakeServerProtocol
import com.github.karbonpowered.network.NetworkServer
import com.github.karbonpowered.network.session.Session
import com.github.karbonpowered.protocol.java.MinecraftSession
import java.net.InetSocketAddress

class Server : NetworkServer() {
    val _sessions = HashSet<Session>()
    val sessions: Set<Session> get() = _sessions

    override fun newSession(channel: io.netty.channel.Channel): Session {
        val session = MinecraftSession(channel, HandshakeServerProtocol)
        _sessions.add(session)
        println("$session connected")
        return session
    }

    override fun sessionInactivated(session: Session) {
        _sessions.remove(session)
        println("$session inactivated")
    }

    override fun onBindSuccess(address: InetSocketAddress) {
        println("Server started at ${address.address.hostAddress}:${address.port}")
    }
}