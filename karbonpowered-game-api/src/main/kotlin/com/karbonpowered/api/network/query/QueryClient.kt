package com.karbonpowered.api.network.query

import com.karbonpowered.api.MinecraftVersion
import java.net.InetSocketAddress

interface QueryClient {
    val address: InetSocketAddress

    val version: MinecraftVersion

    val virtualHost: InetSocketAddress?
}