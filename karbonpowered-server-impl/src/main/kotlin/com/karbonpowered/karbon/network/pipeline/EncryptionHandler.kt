package com.karbonpowered.karbon.network.pipeline

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageCodec
import java.nio.ByteBuffer
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class EncryptionHandler(
    val sharedSecret: SecretKey
) : MessageToMessageCodec<ByteBuf, ByteBuf>() {
    private val encodeBuf = CryptBuf(Cipher.ENCRYPT_MODE, sharedSecret)
    private val decodeBuf = CryptBuf(Cipher.DECRYPT_MODE, sharedSecret)

    override fun encode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) = encodeBuf.crypt(msg, out)

    override fun decode(ctx: ChannelHandlerContext, msg: ByteBuf, out: MutableList<Any>) = decodeBuf.crypt(msg, out)

    private class CryptBuf(
        val mode: Int,
        val sharedSecret: SecretKey
    ) {
        private val cipher = Cipher.getInstance("AES/CFB8/NoPadding").apply {
            init(mode, sharedSecret, IvParameterSpec(sharedSecret.encoded))
        }

        fun crypt(msg: ByteBuf, out: MutableList<Any>) {
            val outBuffer = ByteBuffer.allocate(msg.readableBytes())
            cipher.update(msg.nioBuffer(), outBuffer)
            outBuffer.flip()
            out.add(Unpooled.wrappedBuffer(outBuffer))
        }
    }
}