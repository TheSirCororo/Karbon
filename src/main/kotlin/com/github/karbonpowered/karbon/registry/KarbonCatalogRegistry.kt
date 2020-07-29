package com.github.karbonpowered.karbon.registry

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.catalog.CatalogRegistry
import com.github.karbonpowered.api.catalog.CatalogType
import com.github.karbonpowered.api.entity.living.humanoid.player.gamemode.GameMode
import com.github.karbonpowered.api.entity.living.humanoid.player.hand.HandType
import com.github.karbonpowered.api.entity.living.humanoid.player.modelpart.ModelPart
import com.github.karbonpowered.api.nbt.BinaryTagType
import com.github.karbonpowered.api.registry.DuplicateRegistrationException
import com.github.karbonpowered.api.registry.UnknownTypeException
import com.github.karbonpowered.api.text.chat.ChatVisibility
import com.github.karbonpowered.api.text.chat.MessagePosition
import com.github.karbonpowered.api.text.format.TextColor
import com.github.karbonpowered.api.text.serializer.FormattingCodeTextSerializer
import com.github.karbonpowered.api.text.serializer.SafeTextSerializer
import com.github.karbonpowered.api.text.serializer.TextSerializer
import com.github.karbonpowered.karbon.entity.living.humanoid.player.gamemode.KarbonGameMode
import com.github.karbonpowered.karbon.entity.living.humanoid.player.hand.KarbonHandType
import com.github.karbonpowered.karbon.entity.living.humanoid.player.modelpart.KarbonModelPart
import com.github.karbonpowered.karbon.text.chat.KarbonChatVisibility
import com.github.karbonpowered.karbon.text.chat.KarbonMessagePosition
import com.github.karbonpowered.text.format.KarbonTextColor
import com.github.karbonpowered.text.serializer.KarbonFormattingCodeTextSerializer
import com.github.karbonpowered.text.serializer.KarbonSafeTextSerializer
import com.github.karbonpowered.text.serializer.KarbonTextSerializer
import com.karbonpowered.nbt.KarbonBinaryTagType
import java.util.function.Supplier
import java.util.stream.Stream
import kotlin.reflect.KClass

object KarbonCatalogRegistry : CatalogRegistry {
    val providers = HashMap<Class<out CatalogType>, MutableMap<String, Supplier<out CatalogType>>>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : CatalogType, E : T> getProvider(typeClass: Class<T>, suggestedId: String): Supplier<E> {
        val catalogProviders = providers[typeClass] ?: throw UnknownTypeException("Provider for type '$typeClass' has not been registered!")
        val catalogProvider = catalogProviders[suggestedId] ?: throw UnknownTypeException("Provider for type '$typeClass' with id '$suggestedId' has not been registered!")
        return catalogProvider as Supplier<E>
    }

    override fun <T : CatalogType> get(typeClass: Class<T>, key: CatalogKey): T? = getAllOf(typeClass).filter { it.key == key }.findFirst().orElseGet { null }

    @Suppress("UNCHECKED_CAST")
    override fun <T : CatalogType> getAllOf(typeClass: Class<T>): Stream<T> = providers[typeClass]?.values?.stream()?.map { it.get() as T } ?: Stream.empty()

    override fun <T : CatalogType> getAllFor(typeClass: Class<T>, namespace: String): Stream<T> = getAllOf(typeClass).filter { it.key.namespace == namespace }

    private fun <T : CatalogType, E : T> register(typeClass: KClass<T>, suggestedId: String, supplier: Supplier<E>) = register(typeClass.java, suggestedId, supplier)
    private fun <T : CatalogType, E : T> register(typeClass: Class<T>, suggestedId: String, supplier: Supplier<E>) = apply {
       val catalogProviders = providers.getOrPut(typeClass) { HashMap() }
        if (catalogProviders.containsKey(suggestedId)) {
            throw DuplicateRegistrationException("Catalog '$typeClass' with id '$suggestedId' has already registered!")
        }
        catalogProviders[suggestedId] = supplier
    }

    private fun <T : CatalogType, E : T> register(typeClass: KClass<T>, suppliers: Sequence<Pair<String, ()->E>>) = apply {
        suppliers.forEach {
            register(typeClass, it.first, it.second)
        }
    }

    fun registerDefaultCatalogs() {
        register(SafeTextSerializer::class, "PLAIN") { KarbonSafeTextSerializer }
        register(FormattingCodeTextSerializer::class, "FORMATTING_CODE") { KarbonFormattingCodeTextSerializer('&') }
        register(TextSerializer::class, "JSON") { KarbonTextSerializer }
        register(TextColor::class, KarbonTextColor.generate())
        register(MessagePosition::class, KarbonMessagePosition.generate())
        register(ChatVisibility::class, KarbonChatVisibility.generate())
        register(BinaryTagType::class, KarbonBinaryTagType.generate())
        register(GameMode::class, KarbonGameMode.generate())
        register(ModelPart::class, KarbonModelPart.generate())
        register(HandType::class, KarbonHandType.generate())
    }
}