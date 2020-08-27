package com.github.karbonpowered.karbon.registry

import com.github.karbonpowered.api.NamespacedKey
import com.github.karbonpowered.api.catalog.CatalogRegistry
import com.github.karbonpowered.api.catalog.CatalogType
import com.github.karbonpowered.api.chat.ChatVisibility
import com.github.karbonpowered.api.chat.MessagePosition
import com.github.karbonpowered.api.entity.living.humanoid.player.gamemode.GameMode
import com.github.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.github.karbonpowered.api.entity.living.humanoid.player.modelpart.SkinPart
import com.github.karbonpowered.api.item.inventory.ContainerType
import com.github.karbonpowered.api.nbt.BinaryTagType
import com.github.karbonpowered.api.registry.DuplicateRegistrationException
import com.github.karbonpowered.api.registry.UnknownTypeException
import com.github.karbonpowered.api.world.difficulty.Difficulty
import com.github.karbonpowered.karbon.entity.living.humanoid.player.gamemode.KarbonGameMode
import com.github.karbonpowered.karbon.entity.living.humanoid.player.hand.KarbonHandType
import com.github.karbonpowered.karbon.entity.living.humanoid.player.modelpart.KarbonSkinPart
import com.github.karbonpowered.karbon.item.inventory.KarbonContainerType
import com.github.karbonpowered.karbon.text.chat.KarbonChatVisibility
import com.github.karbonpowered.karbon.text.chat.KarbonMessagePosition
import com.github.karbonpowered.karbon.world.difficulty.KarbonDifficulty
import com.github.karbonpowered.nbt.KarbonBinaryTagType
import java.util.function.Supplier
import java.util.stream.Stream
import kotlin.reflect.KClass

class KarbonCatalogRegistry : CatalogRegistry {
    val providers = HashMap<Class<out CatalogType>, MutableMap<String, Supplier<out CatalogType>>>()

    init {
        registerDefaultCatalogs()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : CatalogType, E : T> getProvider(typeClass: Class<T>, suggestedId: String): Supplier<E> {
        val catalogProviders = providers[typeClass]
                ?: throw UnknownTypeException("Provider for type '$typeClass' has not been registered!")
        val catalogProvider = catalogProviders[suggestedId]
                ?: throw UnknownTypeException("Provider for type '$typeClass' with id '$suggestedId' has not been registered!")
        return catalogProvider as Supplier<E>
    }

    override fun <T : CatalogType> get(typeClass: Class<T>, key: NamespacedKey): T? = getAllOf(typeClass).filter { it.key == key }.findFirst().orElseGet { null }

    @Suppress("UNCHECKED_CAST")
    override fun <T : CatalogType> getAllOf(typeClass: Class<T>): Stream<T> = providers[typeClass]?.values?.stream()?.map { it.get() as T }
            ?: Stream.empty()

    override fun <T : CatalogType> getAllFor(typeClass: Class<T>, namespace: String): Stream<T> = getAllOf(typeClass).filter { it.key.namespace == namespace }

    private fun <T : CatalogType, E : T> register(typeClass: KClass<T>, suggestedId: String, supplier: Supplier<E>) = register(typeClass.java, suggestedId, supplier)
    private fun <T : CatalogType, E : T> register(typeClass: Class<T>, suggestedId: String, supplier: Supplier<E>) = apply {
        val catalogProviders = providers.getOrPut(typeClass) { LinkedHashMap() }
        if (catalogProviders.containsKey(suggestedId)) {
            throw DuplicateRegistrationException("Catalog '$typeClass' with id '$suggestedId' has already registered!")
        }
        catalogProviders[suggestedId] = supplier
    }

    private fun <T : CatalogType, E : T> register(typeClass: KClass<T>, suppliers: Sequence<Pair<String, () -> E>>) = apply {
        suppliers.forEach {
            register(typeClass, it.first, it.second)
        }
    }

    fun registerDefaultCatalogs() {
        register(MessagePosition::class, KarbonMessagePosition.generate())
        register(ChatVisibility::class, KarbonChatVisibility.generate())
        register(BinaryTagType::class, KarbonBinaryTagType.generate())
        register(GameMode::class, KarbonGameMode.generate())
        register(Difficulty::class, KarbonDifficulty.generate())
        register(SkinPart::class, KarbonSkinPart.generate())
        register(HandType::class, KarbonHandType.generate())
        register(ContainerType::class, KarbonContainerType.generate())
    }
}