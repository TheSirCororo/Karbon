package com.karbonpowered.network.session

import com.karbonpowered.network.connection.ConnectionSide
import com.karbonpowered.network.connection.RemoteConnection
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.processor.MessageProcessor
import com.karbonpowered.network.protocol.Protocol

interface Session : RemoteConnection {
    val protocol: Protocol
    val processor: MessageProcessor?
    val side: ConnectionSide<out Session>

    fun <T : Message> messageReceived(message: T)

    fun send(vararg messages: Message)
    fun send(messages: Iterable<Message>)
    fun send(messages: Iterator<Message>)

    fun disconnect()

    fun onDisconnect()

    fun onReady()

    fun onInboundThrowable(throwable: Throwable)
}