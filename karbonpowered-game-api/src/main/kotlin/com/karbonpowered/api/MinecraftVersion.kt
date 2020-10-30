package com.karbonpowered.api

import com.karbonpowered.commons.Nameable

interface MinecraftVersion : Comparable<MinecraftVersion>, Nameable {
    override val name: String

    val protocolVersion: Int

    val isLegacy: Boolean
}

fun MinecraftVersion(name: String, protocolVersion: Int, isLegacy: Boolean = false) = object : MinecraftVersion {
    override val name: String = name
    override val protocolVersion: Int = protocolVersion
    override val isLegacy: Boolean = isLegacy

    override fun compareTo(other: MinecraftVersion): Int = this.protocolVersion.compareTo(other.protocolVersion)
}