package com.karbonpowered.network

import com.karbonpowered.network.connection.ConnectionManager
import com.karbonpowered.network.session.ClientSession
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetSocketAddress

abstract class NetworkClient<T : ClientSession> : ConnectionManager<T> {
    private val workerGroup = NioEventLoopGroup()
    private val bootstrap = Bootstrap()
        .group(workerGroup)
        .channel(NioSocketChannel::class.java)
        .handler(BasicChannelInitializer(this))

    fun connect(address: InetSocketAddress): ChannelFuture =
        bootstrap.connect(address).addListener {
            if (it.isSuccess) {
                onConnectSuccess(address)
            } else {
                onConnectFailure(address, it.cause())
            }
        }

    fun <T> preConnectOption(option: ChannelOption<T>, value: T) {
        bootstrap.option(option, value)
    }

    open fun onConnectSuccess(address: InetSocketAddress) {
    }

    open fun onConnectFailure(address: InetSocketAddress, throwable: Throwable) {
    }

    override fun shutdown() {
        workerGroup.shutdownGracefully()
    }
}