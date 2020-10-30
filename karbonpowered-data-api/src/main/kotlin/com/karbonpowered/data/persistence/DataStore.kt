package com.karbonpowered.data.persistence

import com.karbonpowered.data.DataManipulator
import com.karbonpowered.data.value.Value

interface DataStore {
    fun serialize(dataManipulator: DataManipulator, view: DataView = DataContainer.createNew()): DataView

    fun serialize(values: Iterable<Value<*>>, view: DataView = DataContainer.createNew()): DataView =
        serialize(DataManipulator.immutableOf(values), view)

    fun deserialize(dataManipulator: DataManipulator, view: DataView)

    fun deserialize(view: DataView): DataManipulator.Mutable = DataManipulator.mutableOf().also {
        deserialize(it, view)
    }
}