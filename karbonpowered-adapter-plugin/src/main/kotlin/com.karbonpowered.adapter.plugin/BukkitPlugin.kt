package com.karbonpowered.adapter.plugin

import com.karbonpowered.adapter.bukkit.BukkitAdapter
import org.bukkit.plugin.java.JavaPlugin

class BukkitPlugin : JavaPlugin() {
    override fun onLoad() {
        BukkitAdapter.initialize()
    }
}