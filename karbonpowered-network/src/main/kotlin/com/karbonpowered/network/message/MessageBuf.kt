package com.karbonpowered.network.message

import io.netty.buffer.ByteBuf
import java.io.InputStream
import java.io.OutputStream
import java.util.*

interface MessageBuf {
    fun capacity(): Int
    fun available(): Int
    fun release(): Boolean

    var readerIndex: Int
    var writerIndex: Int

    fun setIndex(readerIndex: Int, writerIndex: Int): MessageBuf

    fun clear(): MessageBuf

    fun markRead(): MessageBuf
    fun markWrite(): MessageBuf

    fun resetRead(): MessageBuf
    fun resetWrite(): MessageBuf

    fun hasArray(): Boolean
    fun array(): ByteArray

    fun writeByte(data: Int): MessageBuf
    fun writeByte(data: Byte): MessageBuf
    fun readByte(): Byte
    fun readUnsignedByte(): Short

    fun writeByteArray(data: ByteArray, start: Int = 0, length: Int = data.size): MessageBuf
    fun readByteArray(): ByteArray

    fun writeBytes(data: ByteArray, start: Int = 0, length: Int = data.size): MessageBuf
    fun readBytes(length: Int): ByteArray
    fun readBytes(index: Int, length: Int): ByteArray
    fun readBytes(dest: ByteArray, index: Int, length: Int): MessageBuf

    fun writeBoolean(data: Boolean): MessageBuf
    fun readBoolean(): Boolean

    fun writeShort(data: Int): MessageBuf
    fun writeShort(data: Short): MessageBuf
    fun readShort(): Short

    fun writeChar(data: Int): MessageBuf
    fun writeChar(data: Char): MessageBuf
    fun readChar(): Char

    fun writeInt(data: Int): MessageBuf
    fun readInt(): Int

    fun writeLong(data: Long): MessageBuf
    fun readLong(): Long

    fun writeFloat(data: Float): MessageBuf
    fun readFloat(): Float

    fun writeDouble(data: Double): MessageBuf
    fun readDouble(): Double

    fun writeVarInt(data: Int): MessageBuf
    fun readVarInt(): Int

    fun writeVarLong(data: Long): MessageBuf
    fun readVarLong(): Long

    fun writeString(data: String): MessageBuf
    fun readString(): String

    fun writeUTF(data: String): MessageBuf
    fun readUTF(): String

    fun writeUniqueId(data: UUID): MessageBuf
    fun readUniqueId(): UUID

    fun asOutputStream(): OutputStream
    fun asInputStream(): InputStream
    fun toByteBuf(): ByteBuf
}