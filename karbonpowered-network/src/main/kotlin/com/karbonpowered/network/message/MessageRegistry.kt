package com.karbonpowered.network.message

import kotlin.reflect.KClass

interface MessageRegistry {
    val bindings: Collection<MessageBinding<*>>

    fun <M : Message> getBinding(message: Class<M>): MessageBinding<M>
    fun <M : Message> getBinding(message: KClass<M>): MessageBinding<M> = getBinding(message.java)

    fun getBinding(opcode: Int): MessageBinding<*>
}