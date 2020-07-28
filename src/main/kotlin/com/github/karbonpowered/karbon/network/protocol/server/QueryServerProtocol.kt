package com.github.karbonpowered.karbon.network.protocol.server

import com.github.karbonpowered.api.network.query.Favicon
import com.github.karbonpowered.api.text.Text
import com.github.karbonpowered.api.text.format.TextColor
import com.github.karbonpowered.api.text.format.TextColors
import com.github.karbonpowered.karbon.KarbonMinecraftVersion
import com.github.karbonpowered.karbon.network.query.KarbonQueryResponse
import com.github.karbonpowered.protocol.java.c2s.query.QueryPingC2SPacket
import com.github.karbonpowered.protocol.java.c2s.query.QueryRequestC2SPacket
import com.github.karbonpowered.protocol.java.protocol.QueryProtocol
import com.github.karbonpowered.protocol.java.s2c.query.QueryPongS2CPacket
import com.github.karbonpowered.protocol.java.s2c.query.QueryResponseS2CPacket
import java.awt.Color
import java.net.URL

object QueryServerProtocol : QueryProtocol(true) {
    init {
        registerHandler<QueryRequestC2SPacket> { session, packet ->

            session.send(QueryResponseS2CPacket(
                KarbonQueryResponse(
                    Text.of(TextColors.RED, "Hello", TextColors.GREEN, "World", TextColor.of(Color(0x117B5E)), " AYAYAYAY"),
                    Favicon.load(URL("https://i.imgur.com/4DX7jzm.png")), null, KarbonMinecraftVersion(false,"1.16.1",736)
                )
            ))
            
        }
        registerHandler<QueryPingC2SPacket> { session, packet ->
            session.send(QueryPongS2CPacket(packet.startTime))
        }
    }
}