package com.karbonpowered.text.serializer

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text

interface PlainTextSerializer : TextSerializer<Text, LiteralText, String> {
    companion object : PlainTextSerializer by loadService() {
        val instance: PlainTextSerializer = this
    }
}