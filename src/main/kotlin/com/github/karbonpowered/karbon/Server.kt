package com.github.karbonpowered.karbon

import com.github.karbonpowered.network.NetworkServer
import com.github.karbonpowered.network.connection.ConnectionSide
import com.github.karbonpowered.protocol.java.MinecraftSession
import com.github.karbonpowered.protocol.java.protocol.HandshakeProtocol
import java.net.InetSocketAddress

class Server : NetworkServer<MinecraftSession>() {
    val _sessions = HashSet<MinecraftSession>()
    override val sessions: Set<MinecraftSession> get() = _sessions

    override fun newSession(channel: io.netty.channel.Channel): MinecraftSession {
        val session = MinecraftSession(channel, ConnectionSide.server(), HandshakeProtocol(true))
        _sessions.add(session)
        println("$session connected")
        return session
    }

    override fun sessionInactivated(session: MinecraftSession) {
        _sessions.remove(session)
        println("$session inactivated")
    }

    override fun onBindSuccess(address: InetSocketAddress) {
        println("Server started at ${address.address.hostAddress}:${address.port}")
    }
}