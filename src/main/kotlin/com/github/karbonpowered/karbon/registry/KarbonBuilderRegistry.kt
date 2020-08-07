package com.github.karbonpowered.karbon.registry

import com.github.karbonpowered.api.catalog.NamespacedKey
import com.github.karbonpowered.api.nbt.BinaryTag
import com.github.karbonpowered.api.nbt.CompoundBinaryTag
import com.github.karbonpowered.api.nbt.ListBinaryTag
import com.github.karbonpowered.api.registry.BuilderRegistry
import com.github.karbonpowered.api.registry.DuplicateRegistrationException
import com.github.karbonpowered.api.registry.UnknownTypeException
import com.github.karbonpowered.api.text.LiteralText
import com.github.karbonpowered.commons.builder.ResettableBuilder
import com.github.karbonpowered.karbon.catalog.KarbonNamespacedKey
import com.github.karbonpowered.text.KarbonLiteralText
import com.karbonpowered.nbt.CompoundTagBuilder
import com.karbonpowered.nbt.KarbonListBinaryTag
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

object KarbonBuilderRegistry : BuilderRegistry {
    private val builders = ConcurrentHashMap<Class<*>, () -> Any>()

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
        registerText()
        registerNbt()
    }.also {
        println("[Karbon] Registered ${builders.size} builders in $it")
    }

    private fun registerText() {
        registerBuilder(LiteralText.Builder::class) { KarbonLiteralText.Builder() }
    }

    private fun registerNbt() {
        registerBuilder(CompoundBinaryTag.Builder::class) { CompoundTagBuilder() }
        registerBuilder(ListBinaryTag.Builder::class) { KarbonListBinaryTag.Builder<BinaryTag>() }
    }
}