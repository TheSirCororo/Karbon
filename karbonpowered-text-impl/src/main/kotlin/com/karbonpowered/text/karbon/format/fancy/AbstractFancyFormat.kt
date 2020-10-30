package com.karbonpowered.text.karbon.format.fancy

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.format.fancy.FancyFormat

abstract class AbstractFancyFormat : FancyFormat {
    override fun format(text: Text): Text {
        if (text !is LiteralText) return text
        init(text.content.length)

        val builder = LiteralText.builder()
        for (c in text.content.toCharArray()) {
            val childBuilder = LiteralText.builder().apply {
                content = c.toString()
                hoverAction = text.hoverAction
                clickAction = text.clickAction
                shiftClickAction = text.shiftClickAction
                color = text.color
                text.decorations.forEach { (decoration, state) ->
                    decoration(decoration, state)
                }
            }
            apply(childBuilder)
            builder.append(childBuilder.build())
        }

        return builder.build()
    }

    abstract fun init(size: Int)
}