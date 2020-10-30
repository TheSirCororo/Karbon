package com.karbonpowered.network.connection

import java.io.Closeable
import java.net.InetSocketAddress

interface RemoteConnection : Closeable {
    val address: InetSocketAddress

    var virtualHost: InetSocketAddress

    override fun close()
}