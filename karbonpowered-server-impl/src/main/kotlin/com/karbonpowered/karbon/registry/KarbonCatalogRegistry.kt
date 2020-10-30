package com.karbonpowered.karbon.registry

import com.karbonpowered.api.chat.ChatVisibility
import com.karbonpowered.api.chat.MessagePosition
import com.karbonpowered.api.entity.living.humanoid.player.gamemode.GameMode
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.karbonpowered.api.entity.living.humanoid.player.modelpart.SkinPart
import com.karbonpowered.api.item.inventory.ContainerType
import com.karbonpowered.api.registry.DuplicateRegistrationException
import com.karbonpowered.api.registry.UnknownTypeException
import com.karbonpowered.api.world.difficulty.Difficulty
import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.data.Key
import com.karbonpowered.item.inventory.KarbonContainerType
import com.karbonpowered.karbon.KeySequenceGenerator
import com.karbonpowered.karbon.entity.living.humanoid.player.gamemode.KarbonGameMode
import com.karbonpowered.karbon.entity.living.humanoid.player.hand.KarbonHandType
import com.karbonpowered.karbon.entity.living.humanoid.player.modelpart.KarbonSkinPart
import com.karbonpowered.karbon.text.chat.KarbonChatVisibility
import com.karbonpowered.karbon.text.chat.KarbonMessagePosition
import com.karbonpowered.karbon.world.difficulty.KarbonDifficulty
import com.karbonpowered.nbt.BinaryTagType
import com.karbonpowered.nbt.KarbonBinaryTagType
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

    override fun <T : CatalogType> get(typeClass: Class<T>, key: NamespacedKey): T? =
        getAllOf(typeClass).filter { it.key == key }.findFirst().orElseGet { null }

    @Suppress("UNCHECKED_CAST")
    override fun <T : CatalogType> getAllOf(typeClass: Class<T>): Stream<T> =
        providers[typeClass]?.values?.stream()?.map { it.get() as T }
            ?: Stream.empty()

    override fun <T : CatalogType> getAllFor(typeClass: Class<T>, namespace: String): Stream<T> =
        getAllOf(typeClass).filter { it.key.namespace == namespace }

    override fun <T : CatalogType, E : T> register(typeClass: KClass<T>, suggestedId: String, supplier: Supplier<E>) =
        register(typeClass.java, suggestedId, supplier)

    override fun <T : CatalogType, E : T> register(typeClass: Class<T>, suggestedId: String, supplier: Supplier<E>) =
        apply {
            val catalogProviders = providers.getOrPut(typeClass) { LinkedHashMap() }
            if (catalogProviders.containsKey(suggestedId)) {
                throw DuplicateRegistrationException("Catalog '$typeClass' with id '$suggestedId' has already registered!")
            }
            catalogProviders[suggestedId] = supplier
        }

    override fun <T : CatalogType, E : T> register(typeClass: KClass<T>, suppliers: Sequence<Pair<String, () -> E>>) =
        apply {
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
        register(Key::class, KeySequenceGenerator.generate())
    }
}