package com.karbonpowered.data.persistence

interface DataBuilder<T : DataSerializable> {
    fun build(container: DataView): T
}