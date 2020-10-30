package com.karbonpowered.network.processor.simple

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.network.processor.MessageProcessor
import io.netty.channel.ChannelHandlerContext
import kotlin.math.min

abstract class SimpleMessageProcessor(
    val capacity: Int = 256
) : MessageProcessor {
    private val decodingByteBuffer = ByteArray(capacity)
    private val encodingByteBuffer = ByteArray(capacity)

    override fun processOutbound(ctx: ChannelHandlerContext, input: MessageBuf, buffer: MessageBuf): MessageBuf {
        while (true) {
            val remaining = input.available()
            if (remaining <= 0) break
            val clamped = min(remaining, capacity)
            input.readBytes(encodingByteBuffer, 0, clamped)
            writeEncode(encodingByteBuffer, clamped)
            while (true) {
                val read = readEncode(encodingByteBuffer)
                if (read <= 0) break
                buffer.writeBytes(encodingByteBuffer, 0, read)
            }
        }
        return buffer
    }

    override fun processInbound(ctx: ChannelHandlerContext, input: MessageBuf, buffer: MessageBuf): MessageBuf {
        while (true) {
            val remaining = input.available()
            if (remaining <= 0) break
            val clamped = min(remaining, capacity)
            input.readBytes(decodingByteBuffer, 0, clamped)
            writeDecode(decodingByteBuffer, clamped)
            while (true) {
                val read = readDecode(decodingByteBuffer)
                if (read <= 0) break
                buffer.writeBytes(decodingByteBuffer, 0, read)
            }
        }
        return buffer
    }

    abstract fun writeEncode(buf: ByteArray, length: Int)

    abstract fun readEncode(buf: ByteArray): Int

    abstract fun writeDecode(buf: ByteArray, length: Int)

    abstract fun readDecode(buf: ByteArray): Int
}