package com.karbonpowered.data.provider.generic

import com.google.common.reflect.TypeToken
import com.karbonpowered.data.DataHolder
import com.karbonpowered.data.Key
import com.karbonpowered.data.provider.AbstractDataProvider
import com.karbonpowered.data.provider.MutableDataProvider
import com.karbonpowered.data.transaction.DataTransactionResult
import com.karbonpowered.data.value.Value
import java.lang.reflect.TypeVariable

@Suppress("UnstableApiUsage", "JAVA_CLASS_ON_COMPANION", "UNCHECKED_CAST")
abstract class GenericMutableDataProviderBase<H, V : Value<E>, E>(
        override val key: Key<V>,
        override val holderType: Class<*> = TypeToken.of(this.javaClass).resolveType(holderTypeParameter).rawType
) : MutableDataProvider<V, E>(), AbstractDataProvider.KnownHolderType {

    private fun isTypeAllowed(dataHolder: DataHolder) = holderType.isInstance(dataHolder)

    protected open fun supports(dataHolder: H): Boolean = true

    protected abstract fun getFrom(dataHolder: H): E?

    open fun getValueFrom(dataHolder: H): V? = getFrom(dataHolder)?.let { constructValue(dataHolder, it) }

    protected open operator fun set(dataHolder: H, value: E): Boolean = false

    protected open fun setAndGetResult(dataHolder: H, value: E): DataTransactionResult {
        val originalValue = getFrom(dataHolder)?.let { constructValue(dataHolder, it) }?.asImmutable()
        val replacementValue = Value.immutableOf(key, value)
        return try {
            if (set(dataHolder, value)) {
                val builder = DataTransactionResult.builder()
                originalValue?.let { builder.replace(it) }
                builder.result(DataTransactionResult.Type.SUCCESS).success(replacementValue).build()
            } else DataTransactionResult.failResult(replacementValue)
        } catch (e: Exception) {
            DataTransactionResult.errorResult(e, replacementValue)
        }
    }

    protected open fun constructValue(dataHolder: H, element: E): V = Value.genericImmutableOf(key, element)

    protected open fun delete(dataHolder: H): Boolean = false

    protected open fun deleteAndGetResult(dataHolder: H): DataTransactionResult {
        val originalValue = getFrom(dataHolder)?.let { constructValue(dataHolder, it) }?.asImmutable()
                ?: return DataTransactionResult.failNoData()
        return try {
            if (delete(dataHolder)) return DataTransactionResult.successRemove(originalValue)
            DataTransactionResult.failNoData()
        } catch (e: Exception) {
            DataTransactionResult.errorResult(e, originalValue)
        }
    }

    override fun isSupported(dataHolder: DataHolder): Boolean = isTypeAllowed(dataHolder) && supports(dataHolder as H)

    override fun getValue(dataHolder: DataHolder): V? = if (isSupported(dataHolder)) getValueFrom(dataHolder as H) else null

    override fun get(dataHolder: DataHolder): E? = if (isSupported(dataHolder)) getFrom(dataHolder as H) else null

    override fun offerValue(dataHolder: DataHolder.Mutable, value: V): DataTransactionResult {
        if (!isSupported(dataHolder)) return DataTransactionResult.failNoData()

        val originalValue = getFrom(dataHolder as H)?.let { constructValue(dataHolder, it) }?.asImmutable()
        val replacementValue = value.asImmutable()
        return try {
            if (set(dataHolder, value.get())) {
                val builder = DataTransactionResult.builder()
                originalValue?.let { builder.replace(it) }
                builder.result(DataTransactionResult.Type.SUCCESS).success(replacementValue).build()
            } else DataTransactionResult.failResult(replacementValue)
        } catch (e: Exception) {
            DataTransactionResult.errorResult(e, replacementValue)
        }
    }

    override fun offer(dataHolder: DataHolder.Mutable, element: E): DataTransactionResult =
            if (isSupported(dataHolder)) setAndGetResult(dataHolder as H, element)
            else DataTransactionResult.failResult(Value.immutableOf(key, element))

    override fun remove(dataHolder: DataHolder): DataTransactionResult =
            if (isSupported(dataHolder)) deleteAndGetResult(dataHolder as H)
            else DataTransactionResult.failNoData()

    companion object {
        private val holderTypeParameter: TypeVariable<*> = GenericMutableDataProviderBase::class.java.typeParameters[0]
    }
}