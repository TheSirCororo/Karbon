package com.github.karbonpowered.karbon.network.query

import com.github.karbonpowered.api.MinecraftVersion
import com.github.karbonpowered.api.network.query.Favicon
import com.github.karbonpowered.api.network.query.QueryResponse
import com.github.karbonpowered.text.Text

data class KarbonQueryResponse(
        override val description: Text,
        override val favicon: Favicon?,
        override val players: QueryResponse.Players?,
        override val version: MinecraftVersion
) : QueryResponse