package com.github.karbonpowered.karbon

import com.github.karbonpowered.api.Karbon
import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.karbon.profile.KarbonGameProfileManager
import com.github.karbonpowered.karbon.registry.KarbonBuilderRegistry
import com.github.karbonpowered.karbon.registry.KarbonCatalogRegistry
import com.github.karbonpowered.karbon.registry.KarbonFactoryRegistry
import com.github.karbonpowered.protocol.java.MinecraftSession
import com.github.karbonpowered.protocol.java.ProtocolState
import com.github.karbonpowered.protocol.java.s2c.game.GameCustomPayloadS2CPacket
import com.github.karbonpowered.protocol.java.s2c.game.GameKeepAliveS2CPacket
import com.github.karbonpowered.text.translation.KarbonTranslationRegistry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.InetSocketAddress
import kotlin.random.Random


fun main() {
    Karbon.initialize()
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
                    val brand = GameCustomPayloadS2CPacket(CatalogKey.minecraft("brand"), "§bKarbonPowered§f".toByteArray())
                    sessions.forEach {
                        if (it is MinecraftSession && it.protocolState == ProtocolState.GAME)
                            it.send(brand)
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