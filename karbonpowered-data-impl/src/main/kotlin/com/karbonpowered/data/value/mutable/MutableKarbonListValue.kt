package com.karbonpowered.data.value.mutable

import com.karbonpowered.data.copy.CopyHelper
import com.karbonpowered.data.key.KarbonKey
import com.karbonpowered.data.value.ListValue
import java.util.function.Consumer

class MutableKarbonListValue<E>(
    override val key: KarbonKey<ListValue.Mutable<E>, List<E>>,
    override var element: List<E>,
) : MutableKarbonCollectionValue<
        E,
        List<E>,
        MutableList<E>,
        ListValue.Mutable<E>,
        ListValue.Immutable<E>>(), ListValue.Mutable<E> {

    override fun get(index: Int): E = this.element[index]

    override fun indexOf(element: E): Int = this.element.indexOf(element)

    override fun add(index: Int, value: E): ListValue.Mutable<E> = modifyCollection {
        it.add(index, value)
    }

    override fun add(index: Int, values: Iterable<E>): ListValue.Mutable<E> = modifyCollection {
        var offset = 0
        values.forEach { value ->
            it.add(index + offset++, value)
        }
    }

    override fun remove(index: Int): ListValue.Mutable<E> = modifyCollection { it.removeAt(index) }

    override fun set(index: Int, element: E): ListValue.Mutable<E> = modifyCollection { it[index] = element }

    override fun asImmutable(): ListValue.Immutable<E> = key.valueConstructor.getImmutable(element).asImmutable()

    override fun copy(): ListValue.Mutable<E> = MutableKarbonListValue(key, CopyHelper.copy(element))

    override fun modifyCollection(consumer: Consumer<MutableList<E>>): ListValue.Mutable<E> = apply {
        val list = element
        if (list is MutableList<E>) {
            consumer.accept(list)
        } else {
            val copy = ArrayList(list)
            consumer.accept(copy)
            set(copy)
        }
    }
}