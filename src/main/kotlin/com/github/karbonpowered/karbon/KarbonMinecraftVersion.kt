package com.github.karbonpowered.karbon

import com.github.karbonpowered.api.MinecraftVersion

data class KarbonMinecraftVersion(
    override val isLegacy: Boolean,
    override val name: String,
    override val protocolVersion: Int
) : MinecraftVersion {
    override fun compareTo(other: MinecraftVersion): Int = protocolVersion.compareTo(other.protocolVersion)
}