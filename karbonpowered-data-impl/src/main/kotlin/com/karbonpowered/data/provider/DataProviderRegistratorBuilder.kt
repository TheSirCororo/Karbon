package com.karbonpowered.data.provider

import com.karbonpowered.data.DataProvider

abstract class DataProviderRegistratorBuilder {
    val registrator = DataProviderRegistrator(this)

    fun register(provider: DataProvider<*, *>): DataProviderRegistratorBuilder = apply {
        DataProviderRegistry.register(provider)
    }

    abstract fun register()
}