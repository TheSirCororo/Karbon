package com.karbonpowered.network.pipeline

import com.karbonpowered.network.netty.NettyMessageBuf
import com.karbonpowered.network.processor.MessageProcessor
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class MessageProcessorDecoder(
    val channelMessageHandler: ChannelMessageHandler
) : ByteToMessageDecoder() {
    val processor: MessageProcessor?
        get() = channelMessageHandler.session.processor

    override fun decode(ctx: ChannelHandlerContext, input: ByteBuf, out: MutableList<Any>) {
        val processor = processor
        if (processor == null) {
            out.add(input.readBytes(input.readableBytes()))
        } else {
            val buffer = NettyMessageBuf(ctx.alloc().buffer()).also {
                processor.processInbound(ctx, NettyMessageBuf(input), it)
            }
            out.add(buffer.byteBuf)
        }
    }
}