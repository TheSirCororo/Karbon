package com.karbonpowered.text.serializer

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.Text

interface JsonTextSerializer : TextSerializer<Text, Text, String> {
    companion object : JsonTextSerializer by loadService() {
        val instance: JsonTextSerializer = this
    }
}