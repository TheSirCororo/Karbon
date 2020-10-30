package com.karbonpowered.network.processor

import com.karbonpowered.network.message.MessageBuf
import io.netty.channel.ChannelHandlerContext

/**
 * [MessageProcessor] can be used in a [PreprocessReplayingDecoder] or [ProcessingEncoder] to define
 * how a [io.netty.buffer.ByteBuf] should be processed prior to decode or after encode.
 */
interface MessageProcessor {
    /**
     * Adds the data contained in the given channel buffer to the processor and returns the output channel buffer.
     * The function may be called from multiple threads.
     *
     * This is called after [com.karbonpowered.network.codec.Codec.encode], but before the message is sent.
     * {@code input.release} should NOT be called; it is done externally.
     * {@code buffer.release} should NOT be called; it is done externally.
     *
     * @param ctx the channel handler context
     * @param input the buffer containing the input data
     * @param buffer the buffer to add the data to; will be dynamically-sized
     * @return the processed outbound ByteBuf
     */
    fun processOutbound(ctx: ChannelHandlerContext, input: MessageBuf, buffer: MessageBuf): MessageBuf

    /**
     * Adds the data contained in the given channel buffer to the processor and returns the output channel buffer.
     * The method may be called from multiple threads.
     *
     * This should read as much data from [input] as possible. It does not replay.
     *
     * This is called after the message arrives, but before [com.karbonpowered.network.codec.Codec.encode] is called.<br>
     * [input.release] should NOT be called; it is done externally.
     * [input.release] should NOT be called; it is done externally.
     *
     * @param ctx the channel handler context
     * @param input the buffer containing the input data
     * @param buffer the buffer to add the data to; will be dynamically-sized
     * @return the processed inbound buffer
     */
    fun processInbound(ctx: ChannelHandlerContext, input: MessageBuf, buffer: MessageBuf): MessageBuf
}