package com.karbonpowered.network

import com.karbonpowered.network.connection.ConnectionManager
import com.karbonpowered.network.pipeline.*
import com.karbonpowered.network.session.Session
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

open class BasicChannelInitializer(
    val connectionManager: ConnectionManager<out Session>
) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        val handler = ChannelMessageHandler(connectionManager)
        val processorDecoder = MessageProcessorDecoder(handler)
        val processorEncoder = MessageProcessorEncoder(handler)
        val decoder = MessageDecoder(handler)
        val encoder = MessageEncoder(handler)

        ch.pipeline()
            .addLast("processorDecoder", processorDecoder)
            .addLast("decoder", decoder)
            .addLast("processorEncoder", processorEncoder)
            .addLast("encoder", encoder)
            .addLast("handler", handler)
    }
}