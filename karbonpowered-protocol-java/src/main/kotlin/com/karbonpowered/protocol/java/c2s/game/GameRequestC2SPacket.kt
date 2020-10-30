package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket

data class GameRequestC2SPacket
@JvmOverloads
constructor(
    val request: Request = Request.RESPAWN
) : MinecraftPacket() {
    enum class Request : CatalogType {
        RESPAWN, STATISTICS;

        override val key: NamespacedKey = NamespacedKey.karbon(name)
    }

    object Codec : com.karbonpowered.network.codec.Codec<GameRequestC2SPacket> {
        override fun decode(buffer: MessageBuf): GameRequestC2SPacket {
            val request = MagicValues.key(Request::class, buffer.readVarInt())
            return GameRequestC2SPacket(request)
        }

        override fun encode(buffer: MessageBuf, message: GameRequestC2SPacket): MessageBuf {
            buffer.writeVarInt(MagicValues.value(message.request))
            return buffer
        }
    }
}