package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.api.chat.ChatVisibilities
import com.karbonpowered.api.chat.ChatVisibility
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandTypes
import com.karbonpowered.api.entity.living.humanoid.player.modelpart.SkinPart
import com.karbonpowered.api.entity.living.humanoid.player.modelpart.SkinParts
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.util.getBitValue
import com.karbonpowered.protocol.java.util.setBitValue
import java.util.*
import java.util.stream.Collectors

data class GameClientSettingsC2SPacket
@JvmOverloads
constructor(
    val locale: Locale = Locale.getDefault(),
    val viewDistance: Int = 6,
    val chatVisibility: ChatVisibility = ChatVisibilities.FULL,
    val isChatColorsEnabled: Boolean = true,
    val displayedSkinParts: Set<SkinPart> = SkinParts.sequence().toSet(),
    val mainHand: HandType = HandTypes.MAIN_HAND
) : MinecraftPacket() {
    object Codec_1_9 : Codec<GameClientSettingsC2SPacket> {
        override fun decode(buffer: MessageBuf): GameClientSettingsC2SPacket {
            val rawLocale = buffer.readString().split("_")
            val locale = Locale(rawLocale[0], rawLocale[1])
            val viewDistance = buffer.readByte().toInt()
            val chatVisibility = MagicValues.key(ChatVisibility::class, buffer.readVarInt())
            val isChatColorsEnabled = buffer.readBoolean()
            val flags = buffer.readUnsignedByte().toInt()
            val displayedSkinParts = CatalogRegistry.getAllOf(SkinPart::class).filter {
                flags.getBitValue(MagicValues.value(it))
            }.collect(Collectors.toSet())
            val mainHand = MagicValues.key(HandType::class, buffer.readVarInt())
            return GameClientSettingsC2SPacket(
                locale, viewDistance, chatVisibility, isChatColorsEnabled, displayedSkinParts, mainHand
            )
        }

        override fun encode(buffer: MessageBuf, message: GameClientSettingsC2SPacket): MessageBuf {
            buffer.writeString(message.locale.toString())
            buffer.writeByte(message.viewDistance)
            buffer.writeVarInt(MagicValues.value(message.chatVisibility))
            buffer.writeBoolean(message.isChatColorsEnabled)
            var flags = 0
            message.displayedSkinParts.forEach {
                flags = flags.setBitValue(true, MagicValues.value(it))
            }
            buffer.writeByte(flags)
            buffer.writeVarInt(MagicValues.value(message.mainHand))
            return buffer
        }
    }

    object Codec_1_8 : Codec<GameClientSettingsC2SPacket> {
        override fun decode(buffer: MessageBuf): GameClientSettingsC2SPacket {
            val rawLocale = buffer.readString().split("_")
            val locale = Locale(rawLocale[0], rawLocale[1])
            val viewDistance = buffer.readByte().toInt()
            val chatVisibility = MagicValues.key(ChatVisibility::class, buffer.readByte().toInt())
            val isChatColorsEnabled = buffer.readBoolean()
            val flags = buffer.readUnsignedByte().toInt()
            val displayedSkinParts = CatalogRegistry.getAllOf(SkinPart::class).filter {
                flags.getBitValue(MagicValues.value(it))
            }.collect(Collectors.toSet())
            return GameClientSettingsC2SPacket(
                locale, viewDistance, chatVisibility, isChatColorsEnabled, displayedSkinParts
            )
        }

        override fun encode(buffer: MessageBuf, message: GameClientSettingsC2SPacket): MessageBuf {
            buffer.writeString(message.locale.toString())
            buffer.writeByte(message.viewDistance)
            buffer.writeByte(MagicValues.value(message.chatVisibility))
            buffer.writeBoolean(message.isChatColorsEnabled)
            var flags = 0
            message.displayedSkinParts.forEach {
                flags = flags.setBitValue(true, MagicValues.value(it))
            }
            buffer.writeByte(flags)
            return buffer
        }
    }

    object Codec_1_7 : Codec<GameClientSettingsC2SPacket> {
        override fun decode(buffer: MessageBuf): GameClientSettingsC2SPacket {
            val rawLocale = buffer.readString().split("_")
            val locale = Locale(rawLocale[0], rawLocale[1])
            val viewDistance = buffer.readByte().toInt()
            val chatVisibility = MagicValues.key(ChatVisibility::class, buffer.readByte().toInt())
            val isChatColorsEnabled = buffer.readBoolean()
            buffer.readUnsignedByte() // Difficulty (unused)
            val displayedSkinParts = if (buffer.readBoolean()) {
                CatalogRegistry.getAllOf(SkinPart::class).collect(Collectors.toSet())
            } else {
                CatalogRegistry.getAllOf(SkinPart::class).filter { it != SkinParts.CAPE }.collect(Collectors.toSet())
            }
            return GameClientSettingsC2SPacket(
                locale, viewDistance, chatVisibility, isChatColorsEnabled, displayedSkinParts
            )
        }

        override fun encode(buffer: MessageBuf, message: GameClientSettingsC2SPacket): MessageBuf {
            buffer.writeString(message.locale.toString())
            buffer.writeByte(message.viewDistance)
            buffer.writeByte(MagicValues.value(message.chatVisibility))
            buffer.writeBoolean(message.isChatColorsEnabled)
            buffer.writeByte(0) // Difficulty (unused)
            buffer.writeBoolean(message.displayedSkinParts.contains(SkinParts.CAPE))
            return buffer
        }
    }
}