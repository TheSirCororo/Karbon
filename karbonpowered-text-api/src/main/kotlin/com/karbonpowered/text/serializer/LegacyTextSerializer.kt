package com.karbonpowered.text.serializer

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.Text

interface LegacyTextSerializer : TextSerializer<Text, Text, String> {
    companion object : LegacyTextSerializer by loadService() {
        val instance: LegacyTextSerializer = this
    }
}