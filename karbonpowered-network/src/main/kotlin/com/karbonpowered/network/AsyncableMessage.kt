package com.karbonpowered.network

import com.karbonpowered.network.message.Message

interface AsyncableMessage : Message {
    val isAsync: Boolean
}