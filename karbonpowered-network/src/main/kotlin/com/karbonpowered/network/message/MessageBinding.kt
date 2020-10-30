package com.karbonpowered.network.message

import com.karbonpowered.network.codec.Codec

interface MessageBinding<M : Message> {
    val opcode: Int
    val codec: Codec<M>
}