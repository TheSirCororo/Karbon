package com.karbonpowered.network.message

interface Message {
    override fun toString(): String

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}