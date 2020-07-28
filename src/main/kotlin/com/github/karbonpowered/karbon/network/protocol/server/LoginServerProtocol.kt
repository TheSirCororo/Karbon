package com.github.karbonpowered.karbon.network.protocol.server

import com.github.karbonpowered.api.profile.GameProfile
import com.github.karbonpowered.protocol.java.c2s.login.LoginStartC2SPacket
import com.github.karbonpowered.protocol.java.protocol.LoginProtocol
import com.github.karbonpowered.protocol.java.s2c.login.LoginSuccessS2CPacket
import java.util.*

object LoginServerProtocol : LoginProtocol(true) {
    init {
        registerHandler(LoginStartC2SPacket::class) { session, packet ->
            println("$session: $packet")
            session.send(LoginSuccessS2CPacket(GameProfile.of(UUID.randomUUID(), packet.username)))
        }
    }
}