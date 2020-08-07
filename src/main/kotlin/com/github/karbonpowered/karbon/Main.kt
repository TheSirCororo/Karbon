package com.github.karbonpowered.karbon

import com.github.karbonpowered.api.Karbon
import com.github.karbonpowered.api.catalog.NamespacedKey
import com.github.karbonpowered.api.item.inventory.ContainerTypes
import com.github.karbonpowered.api.nbt.CompoundBinaryTag
import com.github.karbonpowered.api.text.Text
import com.github.karbonpowered.api.text.format.TextColor
import com.github.karbonpowered.karbon.profile.KarbonGameProfileManager
import com.github.karbonpowered.karbon.registry.KarbonBuilderRegistry
import com.github.karbonpowered.karbon.registry.KarbonCatalogRegistry
import com.github.karbonpowered.karbon.registry.KarbonFactoryRegistry
import com.github.karbonpowered.protocol.java.MinecraftSession
import com.github.karbonpowered.protocol.java.ProtocolState
import com.github.karbonpowered.protocol.java.data.Item
import com.github.karbonpowered.protocol.java.s2c.game.GameContainerItemsS2CPacket
import com.github.karbonpowered.protocol.java.s2c.game.GameCustomPayloadS2CPacket
import com.github.karbonpowered.protocol.java.s2c.game.GameKeepAliveS2CPacket
import com.github.karbonpowered.protocol.java.s2c.game.GameOpenContainerS2CPacket
import com.github.karbonpowered.text.translation.KarbonTranslationRegistry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.awt.Color
import java.net.InetSocketAddress
import kotlin.random.Random


fun main() {
    Karbon.initialize()
    val empty = CompoundBinaryTag.empty()
    Server().apply {
        GlobalScope.launch {
            launch {
                while (true) {
                    delay(10000)
                    val keepAlive = GameKeepAliveS2CPacket(Random.nextLong())
                    sessions.forEach {
                        if (it is MinecraftSession && it.protocolState == ProtocolState.GAME) {
                            it.send(keepAlive)
                        }
                    }
                }
            }
            launch {
                while (true) {
                    delay(100)
                    val brand = GameCustomPayloadS2CPacket(NamespacedKey.minecraft("brand"), "§bKarbonPowered§f".toByteArray())
                    val openContainer = GameOpenContainerS2CPacket(1, ContainerTypes.HOPPER, Text.of(TextColor.Companion.of(Color(13, 175, 16)), "My Menu"))
                    val containerItems = GameContainerItemsS2CPacket(1, arrayOf(
                            Item(1), Item(123, 5)
                    ))
                    sessions.forEach {
                        if (it is MinecraftSession && it.protocolState == ProtocolState.GAME) {
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

fun Karbon.initialize() {
    factoryRegistry = KarbonFactoryRegistry
    builderRegistry = KarbonBuilderRegistry
    catalogRegistry = KarbonCatalogRegistry
    translationRegistry = KarbonTranslationRegistry
    gameProfileManager = KarbonGameProfileManager

    KarbonFactoryRegistry.registerDefaultFactories()
    KarbonBuilderRegistry.registerDefaultBuilders()
    KarbonCatalogRegistry.registerDefaultCatalogs()
}