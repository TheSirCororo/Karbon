package com.karbonpowered.karbon.network.pipeline

import com.karbonpowered.network.connection.ConnectionManager
import com.karbonpowered.network.pipeline.ChannelMessageHandler
import com.karbonpowered.network.session.Session
import io.netty.channel.ChannelException
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.socket.SocketChannel

private val READ_INDLE_TIMEOUT = 20
private val WRITE_IDLE_TIMEOUT = 15

class KarbonChannelInitializer(
        val connectionManager: ConnectionManager<out Session>
) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        val handler = ChannelMessageHandler(connectionManager)

        try {
            ch.config().setOption(ChannelOption.IP_TOS, 0x18)
        } catch (e: ChannelException) {
            println("Your OS does not support type of service")
        }

        ch.pipeline()
//                .addLast("idle_timeout", IdleStateHandler(READ_INDLE_TIMEOUT, WRITE_IDLE_TIMEOUT, 0))
//                .addLast("legacy_ping", LegacyPingHandler())
//                .addLast("encryption", DummyHandler)
                .addLast("framing", FramingHandler())
//                .addLast("compression", DummyHandler)
                .addLast("codecs", CodecsHandler(handler))
                .addLast("handler", handler)
    }
}