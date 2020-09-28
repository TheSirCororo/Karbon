package com.karbonpowered.karbon.network.pipeline

import com.karbonpowered.network.message.MessageBuffers
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageCodec
import kotlin.experimental.and

class FramingHandler : ByteToMessageCodec<ByteBuf>() {
    private fun readableVarInt(buf: ByteBuf): Boolean {
        if (buf.readableBytes() > 5) {
            return true
        }
        val idx = buf.readerIndex()
        var input: Byte
        do {
            if (buf.readableBytes() < 1) {
                buf.readerIndex(idx)
                return false
            }
            input = buf.readByte()
        } while (input and 0x80.toByte() != 0.toByte())
        buf.readerIndex(idx)
        return true
    }

    override fun encode(ctx: ChannelHandlerContext, msg: ByteBuf, out: ByteBuf) {
        MessageBuffers.wrap(out).writeVarInt(msg.readableBytes())
        out.writeBytes(msg)
    }

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        input.markReaderIndex()
        if (!readableVarInt(input)) {
            return
        }

        // check for contents readability
        val length = MessageBuffers.wrap(input).readVarInt()
        if (input.readableBytes() < length) {
            input.resetReaderIndex()
            return
        }

        // read contents into buf
        val buf = ctx.alloc().buffer(length)
        input.readBytes(buf, length)
        out.add(buf)
    }
}