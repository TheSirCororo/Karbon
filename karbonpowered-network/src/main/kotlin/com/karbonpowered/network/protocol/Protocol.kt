package com.karbonpowered.network.protocol

import com.karbonpowered.commons.Nameable
import com.karbonpowered.network.message.MessageBinding
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.network.message.MessageRegistry

interface Protocol : Nameable, MessageRegistry {
    fun writeHeader(header: MessageBuf, binding: MessageBinding<*>, data: MessageBuf): MessageBuf
    fun readHeader(buf: MessageBuf): MessageBinding<*>
}