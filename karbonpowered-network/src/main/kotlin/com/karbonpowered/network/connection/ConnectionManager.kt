package com.karbonpowered.network.connection

import com.karbonpowered.network.session.Session
import io.netty.channel.Channel

interface ConnectionManager<S : Session> {
    val sessions: Collection<S>

    fun newSession(channel: Channel): S

    fun sessionInactivated(session: S)

    fun shutdown()
}