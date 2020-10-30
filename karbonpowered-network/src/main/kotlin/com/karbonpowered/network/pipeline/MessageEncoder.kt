package com.karbonpowered.network.pipeline

import com.karbonpowered.network.message.Message
import com.karbonpowered.network.netty.NettyMessageBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

class MessageEncoder(
    val channelMessageHandler: ChannelMessageHandler
) : MessageToMessageEncoder<Message>() {
    override fun encode(ctx: ChannelHandlerContext, msg: Message, out: MutableList<Any>) {
        val protocol = channelMessageHandler.session.protocol
        val clazz = msg.javaClass
        val codecRegistration = protocol.getBinding(clazz)

        val messageBuf = NettyMessageBuf(ctx.alloc().buffer()).also {
            codecRegistration.codec.encode(it, msg)
        }
        val headerBuf = NettyMessageBuf(ctx.alloc().buffer()).also {
            protocol.writeHeader(it, codecRegistration, messageBuf)
        }

        out.add(Unpooled.wrappedBuffer(headerBuf.toByteBuf(), messageBuf.toByteBuf()))
    }
}