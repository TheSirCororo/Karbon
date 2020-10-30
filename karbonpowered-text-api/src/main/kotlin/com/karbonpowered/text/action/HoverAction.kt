package com.karbonpowered.text.action

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.Text
import com.karbonpowered.text.TextRepresentable
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextStyle
import com.karbonpowered.text.renderer.TextRenderer
import java.util.function.UnaryOperator

interface HoverAction<R> : TextAction<R>, HoverActionSource<R>, TextStyle.BuilderApplicable {
    override fun apply(builder: TextStyle.Builder) {
        builder.hoverAction(this)
    }

    override fun apply(builder: TextBuilder<*, *>) {
        builder.hoverAction(this)
    }

    override fun asHoverAction(): HoverAction<R> = this

    override fun asHoverAction(op: UnaryOperator<R>): HoverAction<R> = this

    fun <C> render(renderer: TextRenderer<C>, context: C): HoverAction<R>

    interface ShowText : HoverAction<Text>, TextRepresentable {
        override val name: String get() = "show_text"

        override fun toText(): Text = result

        override fun <C> render(renderer: TextRenderer<C>, context: C): ShowText = of(renderer.render(result, context))

        interface Factory {
            fun create(text: Text): ShowText
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(text: Text): ShowText = factory.create(text)
        }
    }
}