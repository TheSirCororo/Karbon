package com.github.karbonpowered.karbon

import com.github.karbonpowered.api.Karbon
import com.github.karbonpowered.karbon.profile.KarbonGameProfileManager
import com.github.karbonpowered.karbon.registry.KarbonBuilderRegistry
import com.github.karbonpowered.karbon.registry.KarbonCatalogRegistry
import com.github.karbonpowered.karbon.registry.KarbonFactoryRegistry
import java.net.InetSocketAddress

fun main() {
    Karbon.initialize()
    Server().bind(InetSocketAddress(2000))
}

fun Karbon.initialize() {
    factoryRegistry = KarbonFactoryRegistry
    builderRegistry = KarbonBuilderRegistry
    catalogRegistry = KarbonCatalogRegistry
    gameProfileManager = KarbonGameProfileManager

    KarbonFactoryRegistry.registerDefaultFactories()
    KarbonBuilderRegistry.registerDefaultBuilders()
    KarbonCatalogRegistry.registerDefaultCatalogs()
}