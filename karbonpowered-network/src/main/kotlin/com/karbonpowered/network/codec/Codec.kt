package com.karbonpowered.network.codec

import com.karbonpowered.network.message.MessageBufDecoder
import com.karbonpowered.network.message.MessageBufEncoder

interface Codec<T : Any> : MessageBufDecoder<T>, MessageBufEncoder<T>