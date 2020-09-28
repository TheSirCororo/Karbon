package com.karbonpowered.karbon.network.pipeline

import com.karbonpowered.network.message.Message
import com.karbonpowered.network.message.MessageBuffers
import com.karbonpowered.network.pipeline.ChannelMessageHandler
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageCodec

class CodecsHandler(
        val channelMessageHandler: ChannelMessageHandler
) : MessageToMessageCodec<ByteBuf, Message>() {
    override fun encode(ctx: ChannelHandlerContext, msg: Message, out: MutableList<Any>) {
        val clazz = msg.javaClass
        val binding = channelMessageHandler.session.protocol.getBinding(clazz)

        val headerBuf = MessageBuffers.wrap(ctx.alloc().buffer(8))
        headerBuf.writeVarInt(binding.opcode)

        var messageBuf = MessageBuffers.wrap(ctx.alloc().buffer())
        messageBuf = binding.codec.encode(messageBuf, msg)

        out.add(Unpooled.wrappedBuffer(headerBuf.toByteBuf(), messageBuf.toByteBuf()))
    }

    override fun decode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) {
        val buf = MessageBuffers.wrap(msg)
        val binding = channelMessageHandler.session.protocol.readHeader(buf)
        val decoded = binding.codec.decode(buf)

        if (msg.readableBytes() > 0) {
            println("Message too long: ${msg.readableBytes()} $decoded")
        }

        out.add(decoded)
    }
}