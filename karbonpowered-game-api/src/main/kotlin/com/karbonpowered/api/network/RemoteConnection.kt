package com.karbonpowered.api.network

import java.net.InetSocketAddress

interface RemoteConnection {
    val address: InetSocketAddress

    val virtualHost: InetSocketAddress
}