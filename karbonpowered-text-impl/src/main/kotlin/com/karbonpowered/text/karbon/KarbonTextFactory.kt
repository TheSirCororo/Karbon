package com.karbonpowered.text.karbon

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TextRepresentable
import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.builder.TextBuilderApplicable
import com.karbonpowered.text.format.TextStyle
import java.net.URL
import java.util.concurrent.Callable
import java.util.function.Supplier

class KarbonTextFactory : Text.Factory {
    private fun convertElement(element: Any?): TextBuilderApplicable = when (element) {
        is TextBuilderApplicable -> element
        is TextRepresentable -> element.toText()
        is URL -> TextActions.openUrl(element)
        is Supplier<*> -> convertElement(element.get())
        is Function0<*> -> convertElement(element.invoke())
        is Callable<*> -> convertElement(element.call())
        else -> LiteralText.of(element.toString())
    }

    override fun create(vararg any: Any?): LiteralText {
        val elements = any.map { convertElement(it) }
        val length = elements.size
        if (length == 0) return Text.empty()
        if (length == 1) {
            val first = elements.first()
            return if (first is TextRepresentable) {
                first.toLiteralText()
            } else {
                Text.empty()
            }
        }
        var builder: LiteralText.Builder? = null
        var style = TextStyle.builder()
        for (element in elements) {
            when (element) {
                is TextStyle -> style = element.toBuilder()
                is TextStyle.BuilderApplicable -> style.apply(element)
                is TextRepresentable -> {
                    val text = element.toText()
                    val result = if (text.hasStyling()) {
                        text.style { it.merge(style.build(), TextStyle.Merge.Strategy.IF_NOT_ABSENT_ON_TARGET) }
                    } else {
                        text.style { it.merge(style.build()) }
                    }
                    style = TextStyle.builder()
                    if (builder == null) {
                        if (result is LiteralText) {
                            builder = result.toBuilder()
                            continue
                        } else {
                            builder = LiteralText.builder()
                        }
                    }
                    result.apply(builder)
                }
            }
        }
        if (builder == null) return Text.empty()
        return builder.build()
    }

    private fun TextRepresentable.toLiteralText(): LiteralText {
        val text = toText()
        return if (text is LiteralText) {
            text
        } else {
            LiteralText.of(text)
        }
    }
}