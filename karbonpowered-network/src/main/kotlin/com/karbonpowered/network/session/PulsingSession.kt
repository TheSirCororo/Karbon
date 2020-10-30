package com.karbonpowered.network.session

import com.karbonpowered.network.AsyncableMessage
import com.karbonpowered.network.connection.ConnectionSide
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.protocol.AbstractProtocol
import io.netty.channel.Channel
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue


open class PulsingSession(
    channel: Channel,
    override val side: ConnectionSide<out PulsingSession>,
    bootstrapProtocol: AbstractProtocol
) : BasicSession(channel, side, bootstrapProtocol) {
    private val messageQueue = ArrayDeque<Message>()
    private val sendQueue = ConcurrentLinkedQueue<Message>()
    var state = State.EXCHANGE_HANDSHAKE

    fun pulse() {
        if (state == State.OPEN) {
            while (true) {
                val message = sendQueue.poll() ?: break
                super.send(message)
            }
        }

        while (true) {
            val message = messageQueue.poll() ?: break
            super.messageReceived(message)
        }
    }

    override fun send(vararg messages: Message) = send(SendType.QUEUE, *messages)
    override fun send(messages: Iterable<Message>) = send(SendType.QUEUE, messages)
    override fun send(messages: Iterator<Message>) = send(SendType.QUEUE, messages)

    fun send(type: SendType, vararg messages: Message) {
        if (type == SendType.QUEUE) {
            sendQueue.addAll(messages)
        } else {
            super.send(*messages)
        }
    }

    fun send(type: SendType, messages: Iterable<Message>) {
        if (type == SendType.QUEUE) {
            sendQueue.addAll(messages)
        } else {
            super.send(messages)
        }
    }

    fun send(type: SendType, messages: Iterator<Message>) {
        if (type == SendType.QUEUE) {
            messages.forEach { sendQueue.add(it) }
        } else {
            super.send(messages)
        }
    }

    override fun <T : Message> messageReceived(message: T) {
        if (message is AsyncableMessage && message.isAsync) {
            super.messageReceived(message)
            return
        }
        messageQueue.add(message)
    }

    enum class SendType {
        OPEN_ONLY,
        QUEUE,
        FORCE
    }

    enum class State {
        EXCHANGE_HANDSHAKE,
        EXCHANGE_IDENTIFICATION,
        EXCHANGE_ENCRYPTION,
        WAITING,
        OPEN
    }
}