package com.karbonpowered.network.exception

class UnknownPacketException(
    message: String,
    val opcode: Int,
    val length: Int
) : Exception(message) {
    companion object {
        private const val serialVersionUID = 2479966238464122702L
    }
}