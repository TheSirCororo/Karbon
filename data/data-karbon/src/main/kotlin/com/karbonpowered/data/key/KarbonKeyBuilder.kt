package com.karbonpowered.data.key

import com.google.common.reflect.TypeToken
import com.karbonpowered.catalog.CatalogBuilder
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.data.Key
import com.karbonpowered.data.value.ListValue
import com.karbonpowered.data.value.SetValue
import com.karbonpowered.data.value.Value
import java.lang.reflect.TypeVariable
import java.util.*
import java.util.function.BiPredicate
import java.util.function.Supplier
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

@Suppress("UNCHECKED_CAST")
class KarbonKeyBuilder<E, V : Value<E>> :
        CatalogBuilder<Key<V>, Key.Builder<E, V>>,
        Key.Builder<E, V> {
    override var key: NamespacedKey? = null
    private var valueToken: TypeToken<V>? = null
    private var comparator: Comparator<in E>? = null
    private var includesTester: BiPredicate<in E, in E>? = null

    override fun <T, B : Value<T>> type(token: TypeToken<B>): KarbonKeyBuilder<T, B> = apply {
        this.valueToken = token as TypeToken<V>
    } as KarbonKeyBuilder<T, B>

    override fun key(key: NamespacedKey): Key.Builder<E, V> = apply {
        this.key = key
    }

    override fun comparator(comparator: Comparator<in E>): KarbonKeyBuilder<E, V> = apply {
        this.comparator = comparator
    }

    override fun includesTester(includesTester: BiPredicate<in E, in E>): KarbonKeyBuilder<E, V> = apply {
        this.includesTester = includesTester
    }

    override fun reset(): KarbonKeyBuilder<E, V> = apply {
        valueToken = null
        includesTester = null
        comparator = null
    }

    override fun build(): Key<V> = build(requireNotNull(key))

    override fun build(key: NamespacedKey): Key<V> {
        val valueToken = requireNotNull(valueToken)
        val elementToken = valueToken.resolveType(valueElementParameter)
        val includesTester = includesTester ?: BiPredicate { _, _ -> false }
        val comparator = comparator ?: if (Comparable::class.java.isAssignableFrom(elementToken.rawType)) {
            Comparator.comparing { it as Comparable<Comparable<*>> }
        } else Comparator { o1, o2 ->
            if (o1 == o2) 0 else if (o1.hashCode() > o2.hashCode()) 1 else -1
        }
        val defaultValueSupplier: Supplier<E?> = when {
            ListValue::class.java.isAssignableFrom(valueToken.rawType) -> Supplier { ArrayList<Any>() as E }
            SetValue::class.java.isAssignableFrom(valueToken.rawType) -> Supplier { HashSet<Any>() as E }
            else -> Supplier { null }
        }
        return KarbonKey(key, valueToken, elementToken!!, comparator!!, includesTester, defaultValueSupplier)
    }

    companion object {
        private val valueElementParameter: TypeVariable<*> = Value::class.java.typeParameters[0]
    }
}