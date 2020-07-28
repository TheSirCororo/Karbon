package com.github.karbonpowered.karbon.registry

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.registry.BuilderRegistry
import com.github.karbonpowered.api.registry.DuplicateRegistrationException
import com.github.karbonpowered.api.registry.UnknownTypeException
import com.github.karbonpowered.api.text.LiteralText
import com.github.karbonpowered.commons.builder.ResettableBuilder
import com.github.karbonpowered.karbon.catalog.KarbonCatalogKey
import com.github.karbonpowered.text.KarbonLiteralText
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object KarbonBuilderRegistry : BuilderRegistry {
    private val builders = ConcurrentHashMap<Class<*>, ()->Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ResettableBuilder<*, in T>> provideBuilder(builderClass: Class<T>): T =
        builders[builderClass]?.invoke() as? T ?: throw UnknownTypeException("Type '$builderClass' has no builder registered!")

    private fun <T : ResettableBuilder<*, in T>> registerBuilder(clazz: Class<T>, builder: ()->T): BuilderRegistry = apply {
        if (builders.containsKey(clazz)) {
            throw DuplicateRegistrationException("Type '$clazz' has already been registered as a builder!")
        }
        builders[clazz] = builder
    }

    private fun <T : ResettableBuilder<*, in T>> registerBuilder(clazz: KClass<T>, builder: ()->T) = registerBuilder(clazz.java, builder)

    fun registerDefaultBuilders() {
        registerBuilder(CatalogKey.Builder::class) { KarbonCatalogKey.Builder() }
        registerBuilder(LiteralText.Builder::class) { KarbonLiteralText.Builder() }
    }
}