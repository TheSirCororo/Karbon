package com.karbonpowered.network.service

import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageHandler

class HandlerLookupService {
    private val handlers = HashMap<Class<out Message>, MutableSet<MessageHandler<out Message, *>>>()

    fun <M : Message> addHandler(clazz: Class<M>, handler: MessageHandler<M, *>): MessageHandler<M, *> {
        val handlers = handlers.getOrPut(clazz) { HashSet() }
        handlers.add(handler)
        return handler
    }

    @Suppress("UNCHECKED_CAST")
    fun <M : Message> find(clazz: Class<M>): Set<MessageHandler<M, *>> =
        (handlers[clazz] ?: emptySet<MessageHandler<M, *>>()) as Set<MessageHandler<M, *>>
}