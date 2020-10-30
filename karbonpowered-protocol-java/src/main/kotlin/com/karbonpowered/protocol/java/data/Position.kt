package com.karbonpowered.protocol.java.data

import com.karbonpowered.network.codec.Codec
import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageBuf

data class Position
@JvmOverloads
constructor(
    val x: Int = 0,
    val y: Int = 0,
    val z: Int = 0
) : Message {
    object Codec1_14 : Codec<Position> {
        private const val POSITION_X_SIZE = 38
        private const val POSITION_Y_SIZE = 12
        private const val POSITION_Z_SIZE = 38
        private const val POSITION_Y_SHIFT = 0xFFF
        private const val POSITION_WRITE_SHIFT = 0x3FFFFFF

        override fun decode(buffer: MessageBuf): Position = buffer.run {
            val value = buffer.readLong()
            val x = (value shr POSITION_X_SIZE).toInt()
            val y = (value and POSITION_Y_SHIFT.toLong()).toInt()
            val z = (value shl 26 shr POSITION_Z_SIZE).toInt()
            return Position(x, y, z)
        }

        override fun encode(buffer: MessageBuf, message: Position): MessageBuf = buffer.run {
            val x = (message.x and POSITION_WRITE_SHIFT).toLong()
            val y = (message.y and POSITION_Y_SHIFT).toLong()
            val z = (message.z and POSITION_WRITE_SHIFT).toLong()
            buffer.writeLong(x shl POSITION_X_SIZE or z shl POSITION_Y_SIZE or y)
        }
    }

    object Codec1_7 : Codec<Position> {
        private const val POSITION_X_SIZE = 38
        private const val POSITION_Y_SIZE = 26
        private const val POSITION_Z_SIZE = 38
        private const val POSITION_Y_SHIFT = 0xFFF
        private const val POSITION_WRITE_SHIFT = 0x3FFFFFF

        override fun decode(buffer: MessageBuf): Position = buffer.run {
            val value = readLong()
            val x = (value shr POSITION_X_SIZE).toInt()
            val y = (value shr POSITION_Y_SIZE and POSITION_Y_SHIFT.toLong()).toInt()
            val z = (value shl POSITION_Z_SIZE shr POSITION_Z_SIZE).toInt()
            return Position(x, y, z)
        }

        override fun encode(buffer: MessageBuf, message: Position): MessageBuf = buffer.apply {
            val x = (message.x and POSITION_WRITE_SHIFT).toLong()
            val y = (message.y and POSITION_Y_SHIFT).toLong()
            val z = (message.z and POSITION_WRITE_SHIFT).toLong()
            writeLong(x shl POSITION_X_SIZE or y shl POSITION_Y_SIZE or z)
        }
    }
}