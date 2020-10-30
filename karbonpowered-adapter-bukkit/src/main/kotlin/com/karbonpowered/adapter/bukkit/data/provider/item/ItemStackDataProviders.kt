package com.karbonpowered.adapter.bukkit.data.provider.item

import com.karbonpowered.data.provider.DataProviderRegistratorBuilder

object ItemStackDataProviders : DataProviderRegistratorBuilder() {
    override fun register() {
        ItemStackData.register(registrator)
        SkullItemStackData.register(registrator)
    }
}