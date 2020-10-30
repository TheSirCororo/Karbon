package com.karbonpowered.network.message

import io.netty.buffer.ByteBufAllocator
import io.netty.buffer.PooledByteBufAllocator
import io.netty.buffer.UnpooledByteBufAllocator

class MessageBufferAllocator(
    private val allocator: ByteBufAllocator
) {
    fun buffer(): MessageBuf = MessageBuffers.wrap(allocator.buffer())

    fun buffer(initialCapacity: Int): MessageBuf = MessageBuffers.wrap(allocator.buffer(initialCapacity))

    fun heapBuffer(): MessageBuf = MessageBuffers.wrap(allocator.heapBuffer())

    fun heapBuffer(initialCapacity: Int): MessageBuf = MessageBuffers.wrap(allocator.heapBuffer(initialCapacity))

    fun directBuffer(): MessageBuf = MessageBuffers.wrap(allocator.directBuffer())

    fun directBuffer(initialCapacity: Int): MessageBuf = MessageBuffers.wrap(allocator.directBuffer(initialCapacity))

    companion object {
        val UNPOOLED = MessageBufferAllocator(UnpooledByteBufAllocator.DEFAULT)

        val POOLED = MessageBufferAllocator(PooledByteBufAllocator.DEFAULT)
    }
}