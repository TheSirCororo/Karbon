package com.karbonpowered.data.persistence

import com.karbonpowered.data.DataManager

interface DataContainer : DataView {
    override fun set(path: DataQuery, value: Any): DataContainer

    override fun remove(path: DataQuery): DataContainer

    companion object {
        @JvmStatic
        fun createNew(): DataContainer = DataManager.createContainer()

        @JvmStatic
        fun createNew(safety: DataView.SafetyMode): DataContainer = DataManager.createContainer(safety)
    }
}

fun DataContainer(block: DataContainer.() -> Unit): DataContainer = DataContainer.createNew().apply(block)