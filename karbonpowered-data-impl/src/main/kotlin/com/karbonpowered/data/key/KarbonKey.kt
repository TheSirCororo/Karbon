package com.karbonpowered.data.key

import com.google.common.reflect.TypeToken
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.data.Key
import com.karbonpowered.data.provider.EmptyDataProvider
import com.karbonpowered.data.value.Value
import com.karbonpowered.data.value.ValueConstructorFactory
import java.util.function.BiPredicate
import java.util.function.Supplier

class KarbonKey<V : Value<E>, E>(
    override val key: NamespacedKey,
    override val valueToken: TypeToken<V>,
    override val elementToken: TypeToken<*>,
    override val elementComparator: Comparator<*>,
    override val elementIncludesTester: BiPredicate<*, *>,
    val defaultValueSupplier: Supplier<E?>
) : Key<V> {
    val emptyDataProvider = EmptyDataProvider(this)
    val valueConstructor = ValueConstructorFactory.getConstructor(this)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KarbonKey<*, *>

        if (valueToken != other.valueToken) return false
        if (elementToken != other.elementToken) return false
        if (elementComparator != other.elementComparator) return false
        if (elementIncludesTester != other.elementIncludesTester) return false
        if (emptyDataProvider != other.emptyDataProvider) return false
        if (valueConstructor != other.valueConstructor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = valueToken.hashCode()
        result = 31 * result + elementToken.hashCode()
        result = 31 * result + elementComparator.hashCode()
        result = 31 * result + elementIncludesTester.hashCode()
        result = 31 * result + emptyDataProvider.hashCode()
        result = 31 * result + valueConstructor.hashCode()
        return result
    }
}