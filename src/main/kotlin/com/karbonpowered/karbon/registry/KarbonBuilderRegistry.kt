package com.karbonpowered.karbon.registry

import com.karbonpowered.api.nbt.BinaryTag
import com.karbonpowered.api.nbt.CompoundBinaryTag
import com.karbonpowered.api.nbt.ListBinaryTag
import com.karbonpowered.api.registry.BuilderRegistry
import com.karbonpowered.api.registry.DuplicateRegistrationException
import com.karbonpowered.api.registry.UnknownTypeException
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.commons.builder.ResettableBuilder
import com.karbonpowered.karbon.catalog.KarbonNamespacedKey
import com.karbonpowered.nbt.CompoundTagBuilder
import com.karbonpowered.nbt.KarbonListBinaryTag
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class KarbonBuilderRegistry : BuilderRegistry {
    private val builders = ConcurrentHashMap<Class<*>, () -> Any>()

    init {
        registerDefaultBuilders()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ResettableBuilder<*, in T>> provideBuilder(builderClass: Class<T>): T =
            builders[builderClass]?.invoke() as? T
                    ?: throw UnknownTypeException("Type '$builderClass' has no builder registered!")

    private fun <T : ResettableBuilder<*, in T>> registerBuilder(clazz: Class<T>, builder: () -> T): BuilderRegistry = apply {
        if (builders.containsKey(clazz)) {
            throw DuplicateRegistrationException("Type '$clazz' has already been registered as a builder!")
        }
        builders[clazz] = builder
    }

    private fun <T : ResettableBuilder<*, in T>> registerBuilder(clazz: KClass<T>, builder: () -> T) = registerBuilder(clazz.java, builder)

    @OptIn(ExperimentalTime::class)
    fun registerDefaultBuilders() = measureTime {
        registerBuilder(NamespacedKey.Builder::class) { KarbonNamespacedKey.Builder() }
        registerNbt()
    }.also {
        println("[Karbon] Registered ${builders.size} builders in $it")
    }

    private fun registerNbt() {
        registerBuilder(CompoundBinaryTag.Builder::class) { CompoundTagBuilder() }
        registerBuilder(ListBinaryTag.Builder::class) { KarbonListBinaryTag.Builder<BinaryTag>() }
    }
}