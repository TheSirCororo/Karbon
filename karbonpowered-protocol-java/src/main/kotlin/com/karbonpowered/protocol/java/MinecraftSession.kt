package com.karbonpowered.protocol.java

import com.karbonpowered.network.connection.ConnectionSide
import com.karbonpowered.network.session.BasicSession
import com.karbonpowered.network.session.ClientSession
import com.karbonpowered.network.session.ServerSession
import io.netty.channel.Channel

open class MinecraftSession(
    channel: Channel,
    override val side: ConnectionSide<out MinecraftSession>,
    bootstrapProtocol: MinecraftProtocol
) : BasicSession(channel, side, bootstrapProtocol), ServerSession, ClientSession {
    var protocolVersion: Int = 0
    var protocolState = ProtocolState.HANDSHAKE
}