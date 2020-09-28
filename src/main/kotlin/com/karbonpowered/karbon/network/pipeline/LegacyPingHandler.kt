package com.karbonpowered.karbon.network.pipeline

import com.karbonpowered.api.MinecraftVersions
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class LegacyPingHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val byteBuf = msg as? ByteBuf ?: return

        byteBuf.markReaderIndex()
        var legacyPingProtocol = false

        try {
            if (byteBuf.readByte() == 0xFE.toByte()) {
                when (byteBuf.readableBytes()) {
                    0 -> {
                        ctx.send("Karbon§0§100")
                        legacyPingProtocol = true
                    }
                    1 -> {
                        ctx.send("§1\\" +
                                "${MinecraftVersions.LATEST.protocolVersion}\\" +
                                "${MinecraftVersions.LATEST.name}\\" +
                                "Karbon\\" +
                                "0\\" +
                                "100"
                        )
                        legacyPingProtocol = true
                    }
                    else -> {
                        if (byteBuf.readByte() == 0x01.toByte() &&
                                byteBuf.readByte() == 0xFA.toByte()) {
                            val pingHeader = byteBuf.readBytes(byteBuf.readShort().toInt() shl 1)
                                    .toString(Charsets.UTF_16BE)
                            if (pingHeader == "MC|PingHost") {
                                val dataLength = byteBuf.readUnsignedShort()
                                val clientVersion = byteBuf.readUnsignedByte()
                                val hostname = byteBuf.readBytes(byteBuf.readShort().toInt() shl 1)
                                        .toString(Charsets.UTF_16BE)
                                byteBuf.readInt() // port unused in this case

                                if (clientVersion >= 73 && byteBuf.readableBytes() == 0 &&
                                        7 + (hostname.length shl 1) == dataLength) {
                                    ctx.send("§1\\" +
                                            "${MinecraftVersions.LATEST.protocolVersion}\\" +
                                            "${MinecraftVersions.LATEST.name}\\" +
                                            "Karbon\\" +
                                            "0\\" +
                                            "100"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } catch (expected: RuntimeException) {
            // Silently catch the exception
        } finally {
            if (!legacyPingProtocol) {
                byteBuf.resetReaderIndex()
                ctx.pipeline().remove("legacy_ping")
                ctx.fireChannelRead(msg)
            }
        }
    }

    private fun ChannelHandlerContext.send(byteBuf: ByteBuf) = writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE)
    private fun ChannelHandlerContext.send(string: String) = send(toResponseByteBuf(string))

    private fun ChannelHandlerContext.toResponseByteBuf(string: String): ByteBuf {
        val byteBuf = alloc().buffer(3 + string.length)

        byteBuf.writeByte(0xFF)
        byteBuf.writeShort(string.length)
        byteBuf.writeBytes(string.toByteArray(Charsets.UTF_16BE))

        return byteBuf
    }
}