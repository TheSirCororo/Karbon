package com.karbonpowered.network.message

import com.karbonpowered.network.session.Session

fun interface MessageHandler<T : Message, S : Session> {
    fun handle(message: T, session: S)
}