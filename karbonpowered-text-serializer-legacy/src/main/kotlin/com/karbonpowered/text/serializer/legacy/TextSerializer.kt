package com.karbonpowered.text.serializer.legacy

import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.LegacyTextSerializer
import com.karbonpowered.text.serializer.adventure.asAdventure
import com.karbonpowered.text.serializer.adventure.asKarbon
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

//TODO: Custom text serializer
class TextSerializer : LegacyTextSerializer {
    override fun deserialize(input: String): Text {
        val component = LegacyComponentSerializer.legacyAmpersand().deserialize(input)
        return component.asKarbon()
    }

    override fun serialize(text: Text): String {
        val component = text.asAdventure()
        return LegacyComponentSerializer.legacyAmpersand().serialize(component)
    }
}