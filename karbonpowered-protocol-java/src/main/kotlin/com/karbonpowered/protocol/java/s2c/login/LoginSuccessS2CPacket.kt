package com.karbonpowered.protocol.java.s2c.login

import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket

data class LoginSuccessS2CPacket(
    val profile: GameProfile
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<LoginSuccessS2CPacket> {
        override fun decode(buffer: MessageBuf): LoginSuccessS2CPacket {
            val uniqueId = buffer.readUniqueId()
            val username = buffer.readString()
            val profile = GameProfile.of(uniqueId, username)
            return LoginSuccessS2CPacket(profile)
        }

        override fun encode(buffer: MessageBuf, message: LoginSuccessS2CPacket): MessageBuf {
            buffer.writeUniqueId(message.profile.uniqueId)
            buffer.writeString(message.profile.name)
            return buffer
        }
    }
}