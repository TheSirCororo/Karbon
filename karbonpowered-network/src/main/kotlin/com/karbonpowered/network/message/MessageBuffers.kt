package com.karbonpowered.network.message

import com.karbonpowered.network.netty.NettyMessageBuf
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled

object MessageBuffers {
    fun wrap(buf: ByteBuf): MessageBuf = NettyMessageBuf(buf)

    fun wrap(bytes: ByteArray): MessageBuf = NettyMessageBuf(Unpooled.wrappedBuffer(bytes))
}