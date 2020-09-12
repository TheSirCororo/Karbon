package com.github.karbonpowered.karbon

import com.github.karbonpowered.karbon.network.pipeline.KarbonChannelInitializer
import com.github.karbonpowered.network.NetworkServer
import com.github.karbonpowered.network.connection.ConnectionSide
import com.github.karbonpowered.protocol.java.MinecraftSession
import com.github.karbonpowered.protocol.java.protocol.HandshakeProtocol
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress

class Server : NetworkServer<MinecraftSession>() {
    val _sessions = HashSet<MinecraftSession>()
    override val sessions: Set<MinecraftSession> get() = _sessions
    override val bootstrap: ServerBootstrap = ServerBootstrap()
            .group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(KarbonChannelInitializer(this))
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_KEEPALIVE, true)

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