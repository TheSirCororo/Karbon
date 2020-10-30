package com.karbonpowered.network

import com.karbonpowered.network.connection.ConnectionManager
import com.karbonpowered.network.session.ServerSession
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress

abstract class NetworkServer<T : ServerSession> : ConnectionManager<T> {
    val bossGroup = NioEventLoopGroup()
    val workerGroup = NioEventLoopGroup()
    open val bootstrap: ServerBootstrap = ServerBootstrap()
        .group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel::class.java)
        .childHandler(BasicChannelInitializer(this))
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childOption(ChannelOption.SO_KEEPALIVE, true)

    fun bind(address: InetSocketAddress): ChannelFuture =
        bootstrap.bind(address).addListener {
            if (it.isSuccess) {
                onBindSuccess(address)
            } else {
                onBindFailure(address, it.cause())
            }
        }

    open fun onBindSuccess(address: InetSocketAddress) {
    }

    open fun onBindFailure(address: InetSocketAddress, throwable: Throwable) {
    }

    override fun shutdown() {
        workerGroup.shutdownGracefully()
        bossGroup.shutdownGracefully()
    }
}