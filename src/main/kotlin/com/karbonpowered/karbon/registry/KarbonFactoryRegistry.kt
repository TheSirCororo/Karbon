package com.karbonpowered.karbon.registry

import com.karbonpowered.api.network.query.Favicon
import com.karbonpowered.api.registry.DuplicateRegistrationException
import com.karbonpowered.api.registry.FactoryRegistry
import com.karbonpowered.api.registry.UnknownTypeException
import com.karbonpowered.karbon.network.query.KarbonFavicon
import com.karbonpowered.nbt.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class KarbonFactoryRegistry : FactoryRegistry {
    private val factories = ConcurrentHashMap<Class<*>, Any>()

    init {
        registerDefaultFactories()
    }

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

    @OptIn(ExperimentalTime::class)
    fun registerDefaultFactories() = measureTime {
        registerNbt()
        registerFactory(Favicon.Factory::class, KarbonFavicon.Factory)
    }.also {
        println("[Karbon] Registered ${factories.size} factories in $it")
    }

    private fun registerNbt() {
        registerFactory(ByteBinaryTag.Factory::class, KarbonByteBinaryTag.Factory)
        registerFactory(ShortBinaryTag.Factory::class, KarbonShortBinaryTag.Factory)
        registerFactory(IntBinaryTag.Factory::class, KarbonIntBinaryTag.Factory)
        registerFactory(LongBinaryTag.Factory::class, KarbonLongBinaryTag.Factory)
        registerFactory(FloatBinaryTag.Factory::class, KarbonFloatBinaryTag.Factory)
        registerFactory(DoubleBinaryTag.Factory::class, KarbonDoubleBinaryTag.Factory)
        registerFactory(ByteArrayBinaryTag.Factory::class, KarbonByteArrayBinaryTag.Factory)
        registerFactory(StringBinaryTag.Factory::class, KarbonStringBinaryTag.Factory)
        registerFactory(ListBinaryTag.Factory::class, KarbonListBinaryTag.Factory)
        registerFactory(CompoundBinaryTag.Factory::class, KarbonCompoundBinaryTag.Factory)
        registerFactory(IntArrayBinaryTag.Factory::class, KarbonIntArrayBinaryTag.Factory)
        registerFactory(LongArrayBinaryTag.Factory::class, KarbonLongArrayBinaryTag.Factory)
        registerFactory(BinaryTagType.Factory::class, KarbonBinaryTagType.Factory)
    }
}