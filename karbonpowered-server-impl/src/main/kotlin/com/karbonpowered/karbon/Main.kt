package com.karbonpowered.karbon

import com.karbonpowered.api.item.inventory.ContainerTypes
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.protocol.java.ProtocolState
import com.karbonpowered.protocol.java.data.Item
import com.karbonpowered.protocol.java.s2c.game.GameContainerItemsS2CPacket
import com.karbonpowered.protocol.java.s2c.game.GameCustomPayloadS2CPacket
import com.karbonpowered.protocol.java.s2c.game.GameKeepAliveS2CPacket
import com.karbonpowered.protocol.java.s2c.game.GameOpenContainerS2CPacket
import com.karbonpowered.text.Text
import com.karbonpowered.text.format.TextColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import kotlin.random.Random


fun main() {
    Server().apply {
        GlobalScope.launch {
            launch {
                while (true) {
                    delay(10000)
                    val keepAlive = GameKeepAliveS2CPacket(Random.nextLong())
                    sessions.forEach {
                        if (it.protocolState == ProtocolState.GAME) {
                            it.send(keepAlive)
                        }
                    }
                }
            }
            launch {
                while (true) {
                    delay(100)
                    val brand =
                        GameCustomPayloadS2CPacket(NamespacedKey.minecraft("brand"), "§bKarbonPowered§f".toByteArray())
                    val openContainer = GameOpenContainerS2CPacket(
                        1,
                        ContainerTypes.HOPPER,
                        Text.of(TextColor.of(13, 175, 16), "My Menu")
                    )
                    val containerItems = GameContainerItemsS2CPacket(
                        1, arrayOf(
                            Item(1), Item(123, 5)
                        )
                    )
                    sessions.forEach {
                        if (it.protocolState == ProtocolState.GAME) {
                            it.send(brand)
                            it.send(openContainer)
                            it.send(containerItems)
                        }
                    }
                }
            }
        }
        bind(InetSocketAddress(2000))
    }
    System.gc()
}

