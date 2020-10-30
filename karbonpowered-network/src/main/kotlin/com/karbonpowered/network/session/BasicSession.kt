package com.karbonpowered.network.session

import com.karbonpowered.commons.Identifiable
import com.karbonpowered.network.connection.ConnectionSide
import com.karbonpowered.network.exception.ConnectionClosedException
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageHandler
import com.karbonpowered.network.processor.MessageProcessor
import com.karbonpowered.network.protocol.AbstractProtocol
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import java.net.InetSocketAddress
import java.util.*

open class BasicSession(
    val channel: Channel,
    override val side: ConnectionSide<out BasicSession>,
    bootstrapProtocol: AbstractProtocol
) : Session, Identifiable {
    override val uniqueId: UUID = UUID.randomUUID()
    override var protocol: AbstractProtocol = bootstrapProtocol
    override var processor: MessageProcessor? = null
    override val address: InetSocketAddress
        get() = channel.remoteAddress() as InetSocketAddress
    override var virtualHost: InetSocketAddress = InetSocketAddress.createUnresolved("", 0)

    val isActive: Boolean
        get() = channel.isActive

    override fun <T : Message> messageReceived(message: T) = handleMessage(message)

    @Suppress("UNCHECKED_CAST")
    private fun handleMessage(message: Message) {
        val messageClass = message.javaClass
        val handlers = protocol.getMessageHandlers(messageClass) as Set<MessageHandler<Message, Session>>
        handlers.forEach {
            try {
                it.handle(message, this)
            } catch (t: Throwable) {
                onHandlerThrowable(message, it, t)
            }
        }
    }

    fun sendWithFuture(message: Message): ChannelFuture {
        if (!channel.isActive) {
            throw ConnectionClosedException("Trying to send a message when a session is inactive!")
        }
        return channel.writeAndFlush(message).addListener {
            if (it.cause() != null) {
                onOutboundThrowable(it.cause())
            }
        }
    }

    override fun send(vararg messages: Message) = messages.forEach { sendWithFuture(it) }
    override fun send(messages: Iterable<Message>) = messages.forEach { sendWithFuture(it) }
    override fun send(messages: Iterator<Message>) = messages.forEach { sendWithFuture(it) }

    override fun disconnect() {
        channel.close()
    }

    override fun onDisconnect() {
    }

    override fun onReady() {
    }

    override fun onInboundThrowable(throwable: Throwable) {
        throwable.printStackTrace()
    }

    open fun onOutboundThrowable(throwable: Throwable) {
        throwable.printStackTrace()
    }

    open fun onHandlerThrowable(message: Message, handle: MessageHandler<*, *>, throwable: Throwable) {
        throwable.printStackTrace()
    }

    fun <T> setOption(option: ChannelOption<T>, value: T) {
        channel.config().setOption(option, value)
    }

    override fun close(): Unit = disconnect()
}