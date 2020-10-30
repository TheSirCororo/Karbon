package com.karbonpowered.text.serializer.adventure

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.action.TextActions
import com.karbonpowered.text.serializer.TextSerializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.TranslatableComponent

object AdventureTextSerializer : TextSerializer<Text, Text, Component> {
    override fun deserialize(input: Component): Text {
        val builder = when (input) {
            is TextComponent -> LiteralText.builder().content(input.content())
            is TranslatableComponent -> TranslatableText.builder().key(input.key())
                .args(input.args().map { it.asKarbon() })
            else -> TODO()
        }
        builder.children = input.children().run { mapTo(ArrayList(size)) { it.asKarbon() } }
        builder.color = input.color()?.asKarbon()
        input.decorations().forEach { (decoration, state) ->
            builder.decoration(decoration.asKarbon(), state.asKarbon())
        }
        builder.clickAction = input.clickEvent()?.asKarbon()
        builder.hoverAction = input.hoverEvent()?.asKarbon()
        builder.shiftClickAction = input.insertion()?.let { TextActions.insertText(it) }
        return builder.build()
    }

    override fun serialize(text: Text): Component {
        val builder = when (text) {
            is LiteralText -> Component.text().content(text.content)
            is TranslatableText -> Component.translatable().key(text.key).args(text.args.map { it.asAdventure() })
            else -> TODO()
        }
        builder.append(text.children.map { it.asAdventure() })
        builder.color(text.color?.asAdventure())
        text.decorations.forEach { (decoration, state) ->
            builder.decoration(decoration.asAdventure(), state.asAdventureTextDecorationState())
        }
        builder.clickEvent(text.clickAction?.asAdventure())
        builder.hoverEvent(text.hoverAction?.asAdventure())
        builder.insertion(text.shiftClickAction?.result?.toString())
        return builder.build()
    }
}