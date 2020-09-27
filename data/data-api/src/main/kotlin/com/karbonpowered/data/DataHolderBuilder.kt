package com.karbonpowered.data

import com.github.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.data.value.Value

interface DataHolderBuilder<H : DataHolder, B : DataHolderBuilder<H, B>> : CopyableBuilder<H, B> {
    fun add(value: Value<*>): B = apply {
        add(value.key as Key<Value<Any>>, value.get() as Any)
    } as B

    fun add(values: Iterable<Value<*>>): B = apply {
        values.forEach { add(it) }
    } as B

    fun add(manipulator: DataManipulator): B = add(manipulator.values)

    fun add(dataHolder: DataHolder): B = add(dataHolder.values)

    fun <V> add(key: Key<Value<V>>, value: V)

    override fun from(value: H): B

    override fun build(): H

    override fun reset(): B

    interface Mutable<H : DataHolder.Mutable, B : Mutable<H, B>> : DataHolderBuilder<H, B>

    interface Immutable<H : DataHolder.Immutable<H>, B : Immutable<H, B>> : DataHolderBuilder<H, B>
}