package com.karbonpowered.protocol.java.s2c.game

import com.karbonpowered.api.item.inventory.ContainerType
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.util.readText
import com.karbonpowered.protocol.java.util.writeText
import com.karbonpowered.text.Text

data class GameOpenContainerS2CPacket(
    val syncId: Int,
    val containerType: ContainerType,
    val title: Text
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GameOpenContainerS2CPacket> {
        override fun decode(buffer: MessageBuf): GameOpenContainerS2CPacket {
            val containerId = buffer.readVarInt()
            val type = MagicValues.key(ContainerType::class, buffer.readVarInt())
            val title = buffer.readText()
            return GameOpenContainerS2CPacket(containerId, type, title)
        }

        override fun encode(buffer: MessageBuf, message: GameOpenContainerS2CPacket): MessageBuf {
            buffer.writeVarInt(message.syncId)
            buffer.writeVarInt(MagicValues.value(message.containerType))
            buffer.writeText(message.title)
            return buffer
        }
    }
}