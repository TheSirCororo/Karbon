package com.karbonpowered.network.exception

class ConnectionClosedException : RuntimeException {
    constructor()
    constructor(message: String) : super(message)

    companion object {
        private const val serialVersionUID = 3998883959660327005
    }
}