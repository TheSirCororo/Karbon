package com.karbonpowered.network.protocol

import java.net.InetSocketAddress
import java.util.*
import java.util.concurrent.ConcurrentHashMap

class ProtocolRegistry<T : Protocol> {
    private val names = ConcurrentHashMap<String, T>()
    private val sockets = ConcurrentHashMap<Int, T>()
    val protocols: Collection<T>
        get() = Collections.unmodifiableCollection(names.values)

    fun registerProtocol(port: Int, protocol: T) {
        names[protocol.name] = protocol
        sockets[port] = protocol
    }

    fun getProtocol(name: String): T? = names[name]

    fun getProtocol(address: InetSocketAddress): T? = getProtocol(address.port)
    fun getProtocol(port: Int): T? = sockets[port]
}