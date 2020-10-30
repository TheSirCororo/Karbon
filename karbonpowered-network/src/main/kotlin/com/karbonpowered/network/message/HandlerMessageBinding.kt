package com.karbonpowered.network.message

import com.karbonpowered.network.connection.ConnectionSide
import com.karbonpowered.network.session.Session
import kotlin.reflect.KClass

interface HandlerMessageBinding<M : Message> : MessageBinding<M> {
    fun <S : Session> addHandler(side: ConnectionSide<S>, handler: MessageHandler<out M, out S>): MessageBinding<M>

    fun <S : Session> addHandler(sessionType: Class<S>, handler: MessageHandler<out M, out S>): MessageBinding<M>
    fun <S : Session> addHandler(sessionType: KClass<S>, handler: MessageHandler<out M, out S>): MessageBinding<M> =
        addHandler(sessionType.java, handler)

    fun addHandler(handler: MessageHandler<out M, Session>)

    fun <S : Session> removeHandler(side: ConnectionSide<S>, handler: MessageHandler<out M, out S>): MessageBinding<M>

    fun <S : Session> removeHandler(side: Class<S>, handler: MessageHandler<out M, out S>): MessageBinding<M>
    fun <S : Session> removeHandler(side: KClass<S>, handler: MessageHandler<out M, out S>): MessageBinding<M> =
        removeHandler(side.java, handler)

    fun removeHandler(handler: MessageHandler<out M, *>): MessageBinding<M>
}