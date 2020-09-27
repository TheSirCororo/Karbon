package com.karbonpowered.data

import com.github.karbonpowered.commons.lang.loadService
import com.karbonpowered.data.value.CopyableValueContainer
import com.karbonpowered.data.value.MergeFunction
import com.karbonpowered.data.value.Value
import com.karbonpowered.data.value.ValueContainer
import java.util.function.Function
import java.util.function.Predicate

interface DataManipulator : CopyableValueContainer {
    override fun copy(): DataManipulator

    fun asMutableCopy(): Mutable

    fun asMutable(): Mutable

    fun asImmutable(): Immutable

    interface Immutable : DataManipulator {
        override fun copy(): Immutable = this

        override fun asImmutable(): Immutable = this

        fun <E> with(key: Key<Value<E>>, value: E) = asMutable().set(key, value).asImmutable()

        @Suppress("UNCHECKED_CAST")
        fun <E> with(value: Value<E>) = with(value.key as Key<Value<E>>, value.get())

        fun without(key: Key<*>): Immutable = asMutable().remove(key).asImmutable()

        fun <E> transform(key: Key<Value<E>>, function: Function<E, E>): Immutable =
                get(key)?.let { with(key, function.apply(it)) } ?: this

        interface Factory {
            fun of(): Immutable

            fun of(values: Iterable<Value<*>>): Immutable

            fun of(valueContainer: ValueContainer<Any?, Any?>): Immutable
        }
    }

    interface Mutable : DataManipulator {
        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>, predicate: Predicate<Key<*>>): Mutable =
                copyFrom(valueContainer, MergeFunction.replacementPreferred(), predicate)

        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>, overlap: MergeFunction<Value<Any>, Any>, predicate: Predicate<Key<*>>): Mutable

        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>, vararg keys: Key<*>): Mutable =
                copyFrom(valueContainer, MergeFunction.replacementPreferred(), *keys)

        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>, overlap: MergeFunction<Value<Any>, Any>, vararg keys: Key<*>): Mutable =
                copyFrom(valueContainer, overlap, keys.asIterable())

        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>, keys: Iterable<Key<*>>): Mutable =
                copyFrom(valueContainer, MergeFunction.replacementPreferred(), keys)

        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>, overlap: MergeFunction<Value<Any>, Any>, keys: Iterable<Key<*>>): Mutable

        fun copyFrom(valueContainer: ValueContainer<Any?, Any?>): Mutable = copyFrom(valueContainer, MergeFunction.replacementPreferred())

        operator fun <E> set(key: Key<Value<E>>, value: E): Mutable

        @Suppress("UNCHECKED_CAST")
        fun set(value: Value<*>): Mutable = set(value.key as Key<Value<Any>>, value.get() as Any)

        fun set(vararg values: Value<*>): Mutable = set(values.asIterable())
        fun set(values: Iterable<Value<*>>): Mutable = apply {
            values.forEach {
                set(it)
            }
        }

        fun <E> transform(key: Key<Value<E>>, function: Function<E, E>): Mutable {
            check(supports(key)) { "The provided key is not supported! $key" }
            return set(key, function.apply(require(key)))
        }

        fun remove(key: Key<*>): Mutable

        override fun asMutable(): Mutable = this

        override fun copy(): Mutable

        interface Factory {
            fun of(): Mutable

            fun of(values: Iterable<Value<*>>): Mutable

            fun of(valueContainer: ValueContainer<Any?, Any?>): Mutable
        }
    }

    companion object {
        @JvmStatic
        fun immutableOf(values: Iterable<Value<*>>): Immutable = loadService<Immutable.Factory>().of(values)

        @JvmStatic
        fun immutableOf(vararg values: Value<*>): Immutable = immutableOf(values.asIterable())

        @JvmStatic
        fun immutableOf(valueContainer: ValueContainer<Any?, Any?>): Immutable = loadService<Immutable.Factory>().of(valueContainer)

        @JvmStatic
        fun immutableOf(): Immutable = loadService<Immutable.Factory>().of()

        @JvmStatic
        fun mutableOf(values: Iterable<Value<*>>): Mutable = loadService<Mutable.Factory>().of(values)

        @JvmStatic
        fun mutableOf(vararg values: Value<*>): Mutable = mutableOf(values.asIterable())

        @JvmStatic
        fun mutableOf(valueContainer: ValueContainer<Any?, Any?>): Mutable = loadService<Mutable.Factory>().of(valueContainer)

        @JvmStatic
        fun mutableOf(): Mutable = loadService<Mutable.Factory>().of()
    }
}