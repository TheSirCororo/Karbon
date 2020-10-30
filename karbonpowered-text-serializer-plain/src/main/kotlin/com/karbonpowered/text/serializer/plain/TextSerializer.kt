package com.karbonpowered.text.serializer.plain

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.serializer.PlainTextSerializer

class TextSerializer : PlainTextSerializer {
    override fun deserialize(input: String): LiteralText {
        return LiteralText.of(input)
    }

    override fun serialize(text: Text): String {
        val sb = StringBuilder()
        serialize(sb, text)
        return sb.toString()
    }

    private fun serialize(sb: StringBuilder, text: Text) {
        when (text) {
            is LiteralText -> sb.append(text.content)
            is TranslatableText -> sb.append(text.key)
            else -> TODO()
        }
        text.children.forEach {
            serialize(sb, it)
        }
    }
}