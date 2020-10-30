package com.karbonpowered.protocol.java

import com.karbonpowered.network.NetworkServer
import io.netty.channel.Channel

class MinecraftServer : NetworkServer<MinecraftSession>() {
    override val sessions: Collection<MinecraftSession> = ArrayList()

    override fun newSession(channel: Channel): MinecraftSession {
        TODO("Not yet implemented")
    }

    override fun sessionInactivated(session: MinecraftSession) {
        TODO("Not yet implemented")
    }
}