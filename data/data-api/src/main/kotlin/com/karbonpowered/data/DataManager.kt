package com.karbonpowered.data

import com.github.karbonpowered.commons.lang.loadService
import com.karbonpowered.data.persistence.DataContainer
import com.karbonpowered.data.persistence.DataView

interface DataManager {
    fun createContainer(): DataContainer

    fun createContainer(safety: DataView.SafetyMode): DataContainer

    companion object : DataManager by loadService()
}