package com.karbonpowered.network.netty

import com.karbonpowered.network.message.MessageBuf
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBufOutputStream
import io.netty.buffer.Unpooled
import java.io.InputStream
import java.io.OutputStream
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

class NettyMessageBuf(
    val byteBuf: ByteBuf = Unpooled.buffer()
) : MessageBuf {
    override fun capacity(): Int = byteBuf.capacity()

    override fun available(): Int = byteBuf.readableBytes()

    override fun release(): Boolean = byteBuf.release()

    override var readerIndex: Int
        get() = byteBuf.readerIndex()
        set(value) {
            byteBuf.readerIndex(value)
        }
    override var writerIndex: Int
        get() = byteBuf.writerIndex()
        set(value) {
            byteBuf.writerIndex(value)
        }

    override fun setIndex(readerIndex: Int, writerIndex: Int): MessageBuf = apply {
        byteBuf.setIndex(readerIndex, readerIndex)
    }

    override fun clear(): MessageBuf = apply {
        byteBuf.clear()
    }

    override fun markRead(): MessageBuf = apply {
        byteBuf.markReaderIndex()
    }

    override fun markWrite(): MessageBuf = apply {
        byteBuf.markWriterIndex()
    }

    override fun resetRead(): MessageBuf = apply {
        byteBuf.resetReaderIndex()
    }

    override fun resetWrite(): MessageBuf = apply {
        byteBuf.resetWriterIndex()
    }

    override fun hasArray(): Boolean = byteBuf.hasArray()

    override fun array(): ByteArray = byteBuf.array()

    override fun writeByte(data: Int): MessageBuf = apply {
        byteBuf.writeByte(data)
    }

    override fun writeByte(data: Byte): MessageBuf = writeByte(data.toInt())

    override fun readByte(): Byte = byteBuf.readByte()

    override fun readUnsignedByte(): Short = byteBuf.readUnsignedByte()

    override fun writeByteArray(data: ByteArray, start: Int, length: Int): MessageBuf = apply {
        writeVarInt(length)
        writeBytes(data, start, length)
    }

    override fun readByteArray(): ByteArray = readBytes(readVarInt())

    override fun writeBytes(data: ByteArray, start: Int, length: Int): MessageBuf = apply {
        for (i in 0 until length) {
            writeByte(data[start + i])
        }
    }

    override fun readBytes(length: Int): ByteArray = ByteArray(length) { readByte() }

    override fun readBytes(index: Int, length: Int): ByteArray {
        val dest = ByteArray(length)
        byteBuf.readBytes(dest, index, length)
        return dest
    }

    override fun readBytes(dest: ByteArray, index: Int, length: Int): MessageBuf = apply {
        byteBuf.readBytes(dest, index, length)
    }

    override fun writeBoolean(data: Boolean): MessageBuf = apply {
        byteBuf.writeBoolean(data)
    }

    override fun readBoolean(): Boolean = byteBuf.readBoolean()

    override fun writeShort(data: Int): MessageBuf = apply {
        byteBuf.writeShort(data)
    }

    override fun writeShort(data: Short): MessageBuf = writeShort(data.toInt())

    override fun readShort(): Short = byteBuf.readShort()

    override fun writeChar(data: Int): MessageBuf = apply {
        byteBuf.writeChar(data)
    }

    override fun writeChar(data: Char): MessageBuf = writeChar(data.toInt())

    override fun readChar(): Char = byteBuf.readChar()

    override fun writeInt(data: Int): MessageBuf = apply {
        byteBuf.writeInt(data)
    }

    override fun readInt(): Int = byteBuf.readInt()

    override fun writeLong(data: Long): MessageBuf = apply {
        byteBuf.writeLong(data)
    }

    override fun readLong(): Long = byteBuf.readLong()

    override fun writeFloat(data: Float): MessageBuf = apply {
        byteBuf.writeFloat(data)
    }

    override fun readFloat(): Float = byteBuf.readFloat()

    override fun writeDouble(data: Double): MessageBuf = apply {
        byteBuf.writeDouble(data)
    }

    override fun readDouble(): Double = byteBuf.readDouble()

    override fun writeVarInt(data: Int): MessageBuf = apply {
        var value = data
        do {
            var temp = (value and 127).toByte()
            value = value ushr 7
            if (value != 0) {
                temp = temp or 128.toByte()
            }
            writeByte(temp)
        } while (value != 0)
    }

    override fun readVarInt(): Int {
        var numRead = 0
        var result = 0
        var read: Byte
        do {
            read = readByte()
            val value = (read and 127).toInt()
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 5) {
                throw RuntimeException("VarInt is too big")
            }
        } while (read and 128.toByte() != 0.toByte())

        return result
    }

    override fun writeVarLong(data: Long): MessageBuf = apply {
        var value = data
        do {
            var temp = (value and 127).toByte()
            value = value ushr 7
            if (value != 0L) {
                temp = temp or 128.toByte()
            }
            writeByte(temp)
        } while (value != 0L)
    }

    override fun readVarLong(): Long {
        var numRead = 0
        var result: Long = 0
        var read: Byte
        do {
            read = readByte()
            val value: Long = (read and 127).toLong()
            result = result or (value shl 7 * numRead)
            numRead++
            if (numRead > 10) {
                throw RuntimeException("VarLong is too big")
            }
        } while (read and 128.toByte() != 0.toByte())

        return result
    }

    override fun writeString(data: String): MessageBuf = apply {
        writeByteArray(data.toByteArray())
    }

    override fun readString(): String = String(readByteArray())

    override fun writeUTF(data: String): MessageBuf = apply {
        val byteArray = data.toByteArray()
        writeInt(byteArray.size)
        writeBytes(byteArray)
    }

    override fun readUTF(): String {
        val byteArray = readBytes(readInt())
        return String(byteArray)
    }

    override fun writeUniqueId(data: UUID): MessageBuf = apply {
        writeLong(data.mostSignificantBits)
        writeLong(data.leastSignificantBits)
    }

    override fun readUniqueId(): UUID = UUID(readLong(), readLong())

    override fun asOutputStream(): OutputStream = ByteBufOutputStream(byteBuf)

    override fun asInputStream(): InputStream = ByteBufInputStream(byteBuf)

    override fun toByteBuf(): ByteBuf = byteBuf
}