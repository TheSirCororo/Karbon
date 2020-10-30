package com.karbonpowered.catalog

import com.karbonpowered.commons.lang.loadService
import java.util.function.Supplier
import java.util.stream.Stream
import kotlin.reflect.KClass

interface CatalogRegistry {
    fun <T : CatalogType, E : T> getProvider(typeClass: Class<T>, suggestedId: String): Supplier<E>
    fun <T : CatalogType, E : T> getProvider(typeClass: KClass<T>, suggestedId: String): Supplier<E> = getProvider(
        typeClass.java,
        suggestedId
    )

    operator fun <T : CatalogType> get(typeClass: Class<T>, key: NamespacedKey): T?
    operator fun <T : CatalogType> get(typeClass: KClass<T>, key: NamespacedKey): T? = get(typeClass.java, key)

    fun <T : CatalogType> getAllOf(typeClass: Class<T>): Stream<T>
    fun <T : CatalogType> getAllOf(typeClass: KClass<T>): Stream<T> = Companion.getAllOf(typeClass.java)

    fun <T : CatalogType> getAllFor(typeClass: Class<T>, namespace: String): Stream<T>
    fun <T : CatalogType> getAllFor(typeClass: KClass<T>, namespace: String): Stream<T> = getAllFor(
        typeClass.java,
        namespace
    )

    fun <T : CatalogType> getAllForMinecraft(typeClass: Class<T>): Stream<T> = getAllFor(
        typeClass,
        NamespacedKey.MINECRAFT_NAMESPACE
    )

    fun <T : CatalogType> getAllForMinecraft(typeClass: KClass<T>): Stream<T> = getAllForMinecraft(typeClass.java)

    fun <T : CatalogType> getAllForKarbon(typeClass: Class<T>): Stream<T> = getAllFor(
        typeClass,
        NamespacedKey.KARBON_NAMESPACE
    )

    fun <T : CatalogType> getAllForKarbon(typeClass: KClass<T>): Stream<T> = getAllForKarbon(typeClass.java)

    fun <T : CatalogType, E : T> register(
        typeClass: KClass<T>,
        suggestedId: String,
        supplier: Supplier<E>
    ): CatalogRegistry

    fun <T : CatalogType, E : T> register(
        typeClass: Class<T>,
        suggestedId: String,
        supplier: Supplier<E>
    ): CatalogRegistry

    fun <T : CatalogType, E : T> register(
        typeClass: KClass<T>,
        suppliers: Sequence<Pair<String, () -> E>>
    ): CatalogRegistry

    companion object : CatalogRegistry by loadService()
}

inline fun <reified T : CatalogType, E : T> CatalogRegistry.getProvider(suggestedId: String): Supplier<E> = getProvider(
    T::class,
    suggestedId
)

inline operator fun <reified T : CatalogType> CatalogRegistry.get(key: NamespacedKey): T? = get(T::class, key)
inline fun <reified T : CatalogType> CatalogRegistry.getAllOf(): Stream<T> = getAllOf(T::class)
inline fun <reified T : CatalogType> CatalogRegistry.getAllFor(namespace: String): Stream<T> = getAllFor(
    T::class,
    namespace
)

inline fun <reified T : CatalogType> CatalogRegistry.getAllForMinecraft(): Stream<T> = getAllForMinecraft(T::class)
inline fun <reified T : CatalogType> CatalogRegistry.getAllForKarbon(): Stream<T> = getAllForKarbon(T::class)