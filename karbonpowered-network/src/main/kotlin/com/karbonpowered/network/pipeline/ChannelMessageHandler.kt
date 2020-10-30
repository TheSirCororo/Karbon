package com.karbonpowered.network.pipeline

import com.karbonpowered.network.connection.ConnectionManager
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.session.Session
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.util.concurrent.atomic.AtomicReference

class ChannelMessageHandler(
    val connectionManager: ConnectionManager<out Session>
) : SimpleChannelInboundHandler<Message>() {
    private val sessionReference = AtomicReference<Session>(null)

    val session: Session
        get() = sessionReference.get()

    override fun channelActive(ctx: ChannelHandlerContext) {
        val channel = ctx.channel()
        val session = connectionManager.newSession(channel)
        if (!sessionReference.compareAndSet(null, session)) {
            throw IllegalStateException("Session may not be set more than once")
        }
        session.onReady()
    }

    @Suppress("UNCHECKED_CAST")
    override fun channelInactive(ctx: ChannelHandlerContext?) {
        val session = sessionReference.get()
        session.disconnect()
        connectionManager as ConnectionManager<Session>
        connectionManager.sessionInactivated(session)
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Message) {
        sessionReference.get().messageReceived(msg)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        sessionReference.get().onInboundThrowable(cause)
    }
}