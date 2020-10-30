package com.karbonpowered.data.value.immutable

import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.ListValue
import com.karbonpowered.data.value.mutable.MutableKarbonListValue
import java.util.function.Consumer

class ImmutableKarbonListValue<E>(
    override val key: KarbonKey<ListValue.Mutable<E>, List<E>>,
    override var element: List<E>
) : ImmutableKarbonCollectionValue<
        E,
        List<E>,
        MutableList<E>,
        ListValue.Immutable<E>,
        ListValue.Mutable<E>>(), ListValue.Immutable<E> {

    override fun get(index: Int): E = element[index]

    override fun indexOf(element: E): Int = this.element.indexOf(element)

    override fun modifyCollection(consumer: Consumer<MutableList<E>>): ListValue.Immutable<E> {
        val list = ArrayList(element)
        consumer.accept(list)
        return key.valueConstructor.getRawImmutable(list).asImmutable()
    }

    override fun with(index: Int, value: E): ListValue.Immutable<E> = modifyCollection { it.add(index, value) }

    override fun with(index: Int, values: Iterable<E>): ListValue.Immutable<E> = modifyCollection { list ->
        var offset = 0
        values.forEach { value ->
            list.add(index + offset++, value)
        }
    }

    override fun without(index: Int): ListValue.Immutable<E> = modifyCollection { list -> list.removeAt(index) }

    override fun set(index: Int, element: E): ListValue.Immutable<E> =
        modifyCollection { list -> list[index] = element }

    override fun with(value: List<E>): ListValue.Immutable<E> =
        key.valueConstructor.getRawImmutable(value).asImmutable()

    override fun asMutable(): ListValue.Mutable<E> = MutableKarbonListValue(key, get())
}