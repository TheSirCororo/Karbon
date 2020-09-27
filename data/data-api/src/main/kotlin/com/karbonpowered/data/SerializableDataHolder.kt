package com.karbonpowered.data

import com.karbonpowered.data.persistence.DataSerializable
import com.karbonpowered.data.persistence.DataView

interface SerializableDataHolder : DataSerializable, CopyableDataHolder {
    fun validateRawData(container: DataView): Boolean

    override fun copy(): SerializableDataHolder

    interface Mutable : SerializableDataHolder, CopyableDataHolder.Mutable {
        fun setRawData(container: DataView)

        override fun copy(): Mutable
    }

    interface Immutable<I : Immutable<I>> : SerializableDataHolder, DataHolder.Immutable<I> {
        fun withRawData(container: DataView): I

        override fun copy(): I
    }
}