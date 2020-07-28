package com.github.karbonpowered.karbon.registry

import com.github.karbonpowered.api.network.query.Favicon
import com.github.karbonpowered.api.registry.DuplicateRegistrationException
import com.github.karbonpowered.api.registry.FactoryRegistry
import com.github.karbonpowered.api.registry.UnknownTypeException
import com.github.karbonpowered.api.text.Text
import com.github.karbonpowered.api.text.format.TextColor
import com.github.karbonpowered.api.text.format.TextFormat
import com.github.karbonpowered.api.text.format.TextStyle
import com.github.karbonpowered.karbon.network.query.KarbonFavicon
import com.github.karbonpowered.text.KarbonTextFactory
import com.github.karbonpowered.text.format.KarbonTextColor
import com.github.karbonpowered.text.format.KarbonTextFormat
import com.github.karbonpowered.text.format.KarbonTextStyle
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object KarbonFactoryRegistry : FactoryRegistry {
    private val factories = ConcurrentHashMap<Class<*>, Any>()

    override fun <T : Any> provideFactory(clazz: KClass<T>): T = provideFactory(clazz.java)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> provideFactory(clazz: Class<T>): T =
        factories[clazz] as? T ?: throw UnknownTypeException("Type '$clazz' has no factory registered!")

    private fun <T : Any> registerFactory(clazz: Class<T>, factory: T): KarbonFactoryRegistry = apply {
        if (factories.containsKey(clazz)) {
            throw DuplicateRegistrationException("Type '$clazz' has already been registered as a factory!")
        }
        factories[clazz] = factory
    }
    private fun <T : Any> registerFactory(clazz: KClass<T>, factory: T) = registerFactory(clazz.java, factory)

    fun registerDefaultFactories() {
        registerFactory(TextStyle.Factory::class, KarbonTextStyle.Factory)
        registerFactory(TextFormat.Factory::class, KarbonTextFormat.Factory)
        registerFactory(TextColor.Factory::class, KarbonTextColor.Factory)
        registerFactory(Text.Factory::class, KarbonTextFactory)
        registerFactory(Favicon.Factory::class, KarbonFavicon.Factory)
    }
}