package com.karbonpowered.protocol.java.protocol

import com.karbonpowered.protocol.java.MinecraftProtocol
import com.karbonpowered.protocol.java.c2s.login.LoginEncryptionResponseC2SPacket
import com.karbonpowered.protocol.java.c2s.login.LoginQueryResponseC2SPacket
import com.karbonpowered.protocol.java.c2s.login.LoginStartC2SPacket
import com.karbonpowered.protocol.java.s2c.login.*

open class LoginProtocol(isServerSide: Boolean) : MinecraftProtocol("login", isServerSide, 8) {
    init {
        registerPacket(true, LoginStartC2SPacket::class, LoginStartC2SPacket.Codec::class)
        registerPacket(true, LoginEncryptionResponseC2SPacket::class, LoginEncryptionResponseC2SPacket.Codec::class)
        registerPacket(true, LoginQueryResponseC2SPacket::class, LoginQueryResponseC2SPacket.Codec::class)

        registerPacket(false, LoginDisconnectS2CPacket::class, LoginDisconnectS2CPacket.Codec::class)
        registerPacket(false, LoginEncryptionRequestS2CPacket::class, LoginEncryptionRequestS2CPacket.Codec::class)
        registerPacket(false, LoginSuccessS2CPacket::class, LoginSuccessS2CPacket.Codec::class)
        registerPacket(false, LoginCompressionS2CPacket::class, LoginCompressionS2CPacket.Codec::class)
        registerPacket(false, LoginQueryRequestS2CPacket::class, LoginQueryRequestS2CPacket.Codec::class)
    }
}