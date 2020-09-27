package com.karbonpowered.data

interface CopyableDataHolder : DataHolder {
    fun copy(): CopyableDataHolder

    interface Mutable : CopyableDataHolder, DataHolder.Mutable

    interface Immutable<I : Immutable<I>> : CopyableDataHolder, DataHolder.Immutable<I>
}