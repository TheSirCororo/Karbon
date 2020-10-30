package com.karbonpowered.network.pipeline

import com.karbonpowered.network.exception.UnknownPacketException
import com.karbonpowered.network.netty.NettyMessageBuf
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ReplayingDecoder

class MessageDecoder(
    val channelMessageHandler: ChannelMessageHandler
) : ReplayingDecoder<ByteBuf>() {
    override fun decode(ctx: ChannelHandlerContext, buf: ByteBuf, out: MutableList<Any>) {
        val protocol = channelMessageHandler.session.protocol
        val inputBuffer = NettyMessageBuf(buf)
        val codec = try {
            protocol.readHeader(inputBuffer)
        } catch (e: UnknownPacketException) {
            val length = e.length
            if (length != -1 && length != 0) {
                buf.readBytes(length)
            }
            throw e
        }.codec

        val decoded = codec.decode(inputBuffer)
        out.add(decoded)
    }
}