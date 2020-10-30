package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.util.getBitValue
import com.karbonpowered.protocol.java.util.setBitValue

data class GamePlayerAbilitiesC2SPacket
@JvmOverloads
constructor(
    val invincible: Boolean = false,
    val canFly: Boolean = false,
    val isFlying: Boolean = false,
    val creative: Boolean = false,
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<GamePlayerAbilitiesC2SPacket> {
        private const val FLAG_INVINCIBLE = 0x01
        private const val FLAG_CAN_FLY = 0x02
        private const val FLAG_IS_FLYING = 0x04
        private const val FLAG_CREATIVE = 0x08

        override fun decode(buffer: MessageBuf): GamePlayerAbilitiesC2SPacket {
            val flags = buffer.readByte().toInt()
            val invincible = flags.getBitValue(FLAG_INVINCIBLE)
            val canFly = flags.getBitValue(FLAG_CAN_FLY)
            val isFlying = flags.getBitValue(FLAG_IS_FLYING)
            val creative = flags.getBitValue(FLAG_CREATIVE)
            return GamePlayerAbilitiesC2SPacket(invincible, canFly, isFlying, creative)
        }

        override fun encode(buffer: MessageBuf, message: GamePlayerAbilitiesC2SPacket): MessageBuf {
            var flags = 0
            flags = flags.setBitValue(message.invincible, FLAG_INVINCIBLE)
            flags = flags.setBitValue(message.canFly, FLAG_CAN_FLY)
            flags = flags.setBitValue(message.isFlying, FLAG_IS_FLYING)
            flags = flags.setBitValue(message.creative, FLAG_CREATIVE)
            buffer.writeByte(flags)
            return buffer
        }
    }
}