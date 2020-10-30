package com.karbonpowered.network.protocol

import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageHandler

abstract class AbstractProtocol(
    override val name: String
) : Protocol {
    open fun <T : Message> getWrappedMessage(dynamicMessage: T) = dynamicMessage

    abstract fun <M : Message> getMessageHandlers(message: Class<M>): Set<MessageHandler<M, *>>
}