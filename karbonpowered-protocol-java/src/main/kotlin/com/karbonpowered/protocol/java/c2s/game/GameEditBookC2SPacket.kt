package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandTypes
import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.Item
import com.karbonpowered.protocol.java.util.readItem
import com.karbonpowered.protocol.java.util.writeItem

data class GameEditBookC2SPacket
@JvmOverloads
constructor(
    val book: Item = Item(),
    val signing: Boolean = false,
    val hand: HandType = HandTypes.MAIN_HAND
) : MinecraftPacket() {
    object Codec_1_13 : Codec<GameEditBookC2SPacket> {
        override fun decode(buffer: MessageBuf): GameEditBookC2SPacket {
            val book = buffer.readItem() ?: Item()
            val signing = buffer.readBoolean()
            val hand = MagicValues.key(HandType::class, buffer.readVarInt())
            return GameEditBookC2SPacket(book, signing, hand)
        }

        override fun encode(buffer: MessageBuf, message: GameEditBookC2SPacket): MessageBuf {
            buffer.writeItem(message.book)
            buffer.writeBoolean(message.signing)
            buffer.writeVarInt(MagicValues.value(message.hand))
            return buffer
        }
    }

    object Codec_1_7 : Codec<GameEditBookC2SPacket> {
        override fun decode(buffer: MessageBuf): GameEditBookC2SPacket {
            val book = buffer.readItem() ?: Item()
            val signing = buffer.readBoolean()
            return GameEditBookC2SPacket(book, signing)
        }

        override fun encode(buffer: MessageBuf, message: GameEditBookC2SPacket): MessageBuf {
            buffer.writeItem(message.book)
            buffer.writeBoolean(message.signing)
            return buffer
        }
    }
}