package com.karbonpowered.network.message

import com.karbonpowered.network.session.Session

interface MessageDispatcher {
    fun isSupportedBy(session: Session): Boolean

    fun sendMessage(session: Session, message: Message)
}