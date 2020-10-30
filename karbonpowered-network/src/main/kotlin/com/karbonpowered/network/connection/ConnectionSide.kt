package com.karbonpowered.network.connection

import com.karbonpowered.network.session.ClientSession
import com.karbonpowered.network.session.ServerSession
import com.karbonpowered.network.session.Session

@Suppress("UNCHECKED_CAST")
class ConnectionSide<S : Session> private constructor() {
    companion object {
        @JvmField
        val CLIENT = ConnectionSide<ClientSession>()

        @JvmField
        val SERVER = ConnectionSide<ServerSession>()

        fun <S : ClientSession> client(): ConnectionSide<S> = CLIENT as ConnectionSide<S>

        fun <S : ServerSession> server(): ConnectionSide<S> = SERVER as ConnectionSide<S>
    }
}