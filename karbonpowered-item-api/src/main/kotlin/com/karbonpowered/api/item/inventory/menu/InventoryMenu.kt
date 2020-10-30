package com.karbonpowered.api.item.inventory.menu

import com.karbonpowered.api.item.inventory.type.ViewableInventory
import com.karbonpowered.text.Text

interface InventoryMenu {
    var inventory: ViewableInventory

    var title: Text


}