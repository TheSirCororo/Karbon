package com.karbonpowered.karbon.network.query

import com.karbonpowered.api.MinecraftVersion
import com.karbonpowered.api.network.query.Favicon
import com.karbonpowered.api.network.query.QueryResponse
import com.karbonpowered.text.Text

data class KarbonQueryResponse(
        override val description: Text,
        override val favicon: Favicon?,
        override val players: QueryResponse.Players?,
        override val version: MinecraftVersion
) : QueryResponse