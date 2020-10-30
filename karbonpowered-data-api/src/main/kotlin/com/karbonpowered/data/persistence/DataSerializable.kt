package com.karbonpowered.data.persistence

interface DataSerializable {
    val contentVersion: Int

    fun toContainer(): DataContainer
}