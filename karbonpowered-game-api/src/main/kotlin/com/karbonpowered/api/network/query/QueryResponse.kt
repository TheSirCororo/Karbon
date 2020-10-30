package com.karbonpowered.api.network.query

import com.karbonpowered.api.MinecraftVersion
import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.text.Text

interface QueryResponse {
    val description: Text

    val players: Players?

    val version: MinecraftVersion

    val favicon: Favicon?

    interface Players {
        val online: Int

        val max: Int

        val profiles: List<GameProfile>
    }
}