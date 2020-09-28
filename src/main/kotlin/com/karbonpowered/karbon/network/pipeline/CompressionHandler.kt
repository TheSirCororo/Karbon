package com.karbonpowered.karbon.network.pipeline

import com.karbonpowered.network.message.MessageBuffers
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.DecoderException
import io.netty.handler.codec.EncoderException
import io.netty.handler.codec.MessageToMessageCodec
import java.util.zip.Deflater
import java.util.zip.Inflater

class CompressionHandler(
        val threshold: Int
) : MessageToMessageCodec<ByteBuf, ByteBuf>() {
    val inflater = Inflater()
    val deflater = Deflater()

    override fun encode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) {
        val prefixBuf = MessageBuffers.wrap(ctx.alloc().buffer(5))
        val contentsBuf: ByteBuf

        if (msg.readableBytes() >= threshold) {
            // message should be compressed
            val index = msg.readerIndex()
            val length = msg.readableBytes()

            val sourceData = ByteArray(length)
            msg.readBytes(sourceData)
            deflater.setInput(sourceData)
            deflater.finish()

            val compressedData = ByteArray(length)
            val compressedLength = deflater.deflate(compressedData)
            deflater.reset()

            if (compressedLength == 0) {
                throw EncoderException("Failed to compress message of size $length")
            } else if (compressedLength >= length) {
                // compression increased the size. threshold is probably too low
                // send as an uncompressed packet
                prefixBuf.writeVarInt(0)
                msg.readerIndex(index)
                msg.retain()
                contentsBuf = msg
            } else {
                prefixBuf.writeVarInt(length)
                contentsBuf = Unpooled.wrappedBuffer(compressedData, 0, compressedLength)
            }
        } else {
            // message should be sent through
            prefixBuf.writeVarInt(0)
            msg.retain()
            contentsBuf = msg
        }

        out.add(Unpooled.wrappedBuffer(prefixBuf.toByteBuf(), contentsBuf))
    }

    override fun decode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) {
        val index = msg.readerIndex()
        val uncompressedSize = MessageBuffers.wrap(msg).readVarInt()

        if (uncompressedSize == 0) {
            // message is uncompressed
            val length = msg.readableBytes()
            if (length >= threshold) {
                throw DecoderException("Received uncompressed message of size $length greater than threshold $threshold")
            }

            val buf = ctx.alloc().buffer(length)
            msg.readBytes(buf, length)
            out.add(buf)
        } else {
            // message is compressed
            val sourceData = ByteArray(msg.readableBytes())
            msg.readBytes(sourceData)
            inflater.setInput(sourceData)

            val destData = ByteArray(uncompressedSize)
            val resultLength = inflater.inflate(destData)
            inflater.reset()

            when {
                resultLength == 0 -> {
                    // might be a leftover from before compression was enabled (no compression header)
                    // uncompressedSize is likely to be < threshold
                    msg.readerIndex(index)
                    msg.retain()
                    out.add(msg)
                }
                resultLength != uncompressedSize ->
                    throw DecoderException("Received compressed message claiming to be of size " +
                            "$uncompressedSize but actually $resultLength")
                else -> out.add(Unpooled.wrappedBuffer(destData))
            }
        }
    }
}