package com.karbonpowered.text.format

import com.karbonpowered.commons.Tristate
import com.karbonpowered.commons.builder.Buildable
import com.karbonpowered.commons.builder.CopyableBuilder
import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.action.*
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.builder.TextBuilderApplicable
import java.util.*
import java.util.function.Consumer

interface TextStyle : Buildable<TextStyle, TextStyle.Builder>, TextFormat {
    val color: TextColor?
    val obfuscated: Tristate
    val bold: Tristate
    val strikethrough: Tristate
    val underlined: Tristate
    val italic: Tristate
    val clickAction: ClickAction<*>?
    val hoverAction: HoverAction<*>?
    val shiftClickAction: ShiftClickAction<*>?
    val decorations: Map<TextDecoration, Tristate>
        get() = mapOf(
            TextDecorations.OBFUSCATED to obfuscated,
            TextDecorations.BOLD to bold,
            TextDecorations.STRIKETHROUGH to strikethrough,
            TextDecorations.UNDERLINED to underlined,
            TextDecorations.ITALIC to italic,
        )

    fun edit(consumer: Consumer<Builder>): TextStyle = edit(consumer, Merge.Strategy.ALWAYS)
    fun edit(consumer: Consumer<Builder>, strategy: Merge.Strategy): TextStyle = build {
        if (strategy == Merge.Strategy.ALWAYS) {
            it.merge(this, strategy)
        }
        consumer.accept(it)
        if (strategy == Merge.Strategy.IF_ABSENT_ON_TARGET) {
            it.merge(this, strategy)
        }
    }

    fun colorIfAbsent(color: TextColor?): TextStyle =
        if (this.color == null) color(color) else this

    fun color(color: TextColor?): TextStyle =
        if (this.color == color) this else builder().from(this).color(color).build()

    fun bold(flag: Boolean?): TextStyle = decoration(TextDecorations.BOLD, flag)
    fun italic(flag: Boolean?): TextStyle = decoration(TextDecorations.ITALIC, flag)
    fun underlined(flag: Boolean?): TextStyle = decoration(TextDecorations.UNDERLINED, flag)
    fun strikethrough(flag: Boolean?): TextStyle = decoration(TextDecorations.STRIKETHROUGH, flag)
    fun obfuscated(flag: Boolean?): TextStyle = decoration(TextDecorations.OBFUSCATED, flag)

    fun hasDecoration(decoration: TextDecoration): Boolean = decoration(decoration) == Tristate.TRUE

    fun decoration(decoration: TextDecoration): Tristate = when (decoration) {
        TextDecorations.BOLD -> bold
        TextDecorations.ITALIC -> italic
        TextDecorations.UNDERLINED -> underlined
        TextDecorations.STRIKETHROUGH -> strikethrough
        TextDecorations.OBFUSCATED -> obfuscated
        else -> throw IllegalArgumentException(decoration.toString())
    }

    fun decoration(decoration: TextDecoration, flag: Boolean): TextStyle =
        decoration(decoration, Tristate.fromBoolean(flag))

    fun decoration(decoration: TextDecoration, flag: Boolean?): TextStyle =
        decoration(decoration, Tristate.fromNullableBoolean(flag))

    fun decoration(decoration: TextDecoration, state: Tristate): TextStyle =
        toBuilder().decoration(decoration, state).build()

    fun decorations(vararg decorations: Pair<TextDecoration, Boolean?>): TextStyle =
        decorations(decorations.map { (key, value) -> key to Tristate.fromNullableBoolean(value) }.toMap())

    fun decorations(decorations: Map<TextDecoration, Tristate>): TextStyle =
        toBuilder().decoration(decorations).build()

    fun clickAction(action: ClickAction<*>?): TextStyle =
        toBuilder().clickAction(action).build()

    fun hoverAction(action: HoverAction<*>?): TextStyle =
        toBuilder().hoverAction(action).build()

    fun shiftClickAction(action: ShiftClickAction<*>?): TextStyle =
        toBuilder().shiftClickAction(action).build()

    fun merge(style: TextStyle): TextStyle =
        toBuilder().merge(style).build()

    fun merge(style: TextStyle, strategy: Merge.Strategy): TextStyle =
        toBuilder().merge(style, strategy).build()

    fun merge(style: TextStyle, vararg merge: Merge): TextStyle =
        toBuilder().merge(style, *merge).build()

    fun merge(style: TextStyle, merges: Iterable<Merge>): TextStyle =
        toBuilder().merge(style, merges).build()

    fun merge(style: TextStyle, strategy: Merge.Strategy, vararg merges: Merge): TextStyle =
        toBuilder().merge(style, strategy, *merges).build()

    fun merge(style: TextStyle, strategy: Merge.Strategy, merges: Iterable<Merge>): TextStyle =
        toBuilder().merge(style, strategy, merges).build()

    fun isEmpty(): Boolean = this == empty()

    fun isNotEmpty(): Boolean = !isEmpty()

    override fun toBuilder(): Builder = builder().from(this)

    override fun apply(builder: TextBuilder<*, *>) {
        builder.style(this)
    }

    interface Builder : CopyableBuilder<TextStyle, Builder> {
        var color: TextColor?
        var obfuscated: Tristate
        var bold: Tristate
        var strikethrough: Tristate
        var underlined: Tristate
        var italic: Tristate
        var clickAction: ClickAction<*>?
        var hoverAction: HoverAction<*>?
        var shiftClickAction: ShiftClickAction<*>?

        fun color(color: TextColor?) = apply {
            this.color = color
        }

        fun colorIfAbsent(color: TextColor?) = apply {
            if (this.color == null) {
                this.color = color
            }
        }

        fun obfuscated(flag: Boolean?) = apply {
            decoration(TextDecorations.OBFUSCATED, flag)
        }

        fun obfuscated(state: Tristate) = apply {
            decoration(TextDecorations.OBFUSCATED, state)
        }

        fun bold(flag: Boolean?) = apply {
            decoration(TextDecorations.BOLD, flag)
        }

        fun bold(state: Tristate) = apply {
            decoration(TextDecorations.BOLD, state)
        }

        fun strikethrough(flag: Boolean?) = apply {
            decoration(TextDecorations.STRIKETHROUGH, flag)
        }

        fun strikethrough(state: Tristate) = apply {
            decoration(TextDecorations.STRIKETHROUGH, state)
        }

        fun underlined(flag: Boolean?) = apply {
            decoration(TextDecorations.UNDERLINED, flag)
        }

        fun underlined(state: Tristate) = apply {
            decoration(TextDecorations.UNDERLINED, state)
        }

        fun italic(flag: Boolean?) = apply {
            decoration(TextDecorations.ITALIC, flag)
        }

        fun italic(state: Tristate) = apply {
            decoration(TextDecorations.ITALIC, state)
        }

        fun decorate(vararg decorations: TextDecoration): Builder = decorate(decorations.asIterable())

        fun decorate(decorations: Iterable<TextDecoration>): Builder = apply {
            decorations.forEach {
                decoration(it, Tristate.TRUE)
            }
        }

        fun decoration(decoration: TextDecoration, flag: Boolean): Builder =
            decoration(decoration, Tristate.fromBoolean(flag))

        fun decoration(decoration: TextDecoration, flag: Boolean?): Builder =
            decoration(decoration, Tristate.fromNullableBoolean(flag))

        fun decoration(decoration: TextDecoration, state: Tristate): Builder = apply {
            when (decoration) {
                TextDecorations.BOLD -> bold = state
                TextDecorations.ITALIC -> italic = state
                TextDecorations.UNDERLINED -> underlined = state
                TextDecorations.STRIKETHROUGH -> strikethrough = state
                TextDecorations.OBFUSCATED -> obfuscated = state
            }
        }

        fun decoration(vararg decorations: Pair<TextDecoration, Boolean?>): Builder = apply {
            decorations.forEach { (decoration, state) -> decoration(decoration, state) }
        }

        fun decoration(decorations: Map<TextDecoration, Tristate>): Builder = apply {
            decorations.forEach { (decoration, state) -> decoration(decoration, state) }
        }

        fun textAction(action: TextAction<*>) = apply {
            action.apply(this)
        }

        fun clickAction(clickAction: ClickAction<*>?) = apply {
            this.clickAction = clickAction
        }

        fun hoverAction(hoverAction: HoverAction<*>?) = apply {
            this.hoverAction = hoverAction
        }

        fun hoverAction(source: HoverActionSource<*>?) = apply {
            this.hoverAction = source?.asHoverAction()
        }

        fun shiftClickAction(shiftClickAction: ShiftClickAction<*>?) = apply {
            this.shiftClickAction = shiftClickAction
        }

        fun merge(style: TextStyle): Builder = merge(style, Merge.Strategy.ALWAYS)

        fun merge(style: TextStyle, strategy: Merge.Strategy): Builder = merge(style, strategy, Merge.ALL)

        fun merge(style: TextStyle, vararg merges: Merge): Builder = merge(style, merges.asIterable())

        fun merge(style: TextStyle, merges: Iterable<Merge>): Builder = merge(style, Merge.Strategy.ALWAYS, merges)

        fun merge(style: TextStyle, strategy: Merge.Strategy, vararg merges: Merge): Builder =
            merge(style, strategy, merges.asIterable())

        fun merge(style: TextStyle, strategy: Merge.Strategy, merges: Iterable<Merge>): Builder {
            if (style.isEmpty() || strategy == Merge.Strategy.NEVER || !merges.iterator().hasNext()) {
                return this
            }

            for (merge in merges) {
                when (merge) {
                    Merge.COLOR -> {
                        val color = style.color
                        if (color != null && strategy.mergeColor(this, color)) {
                            color(color)
                        }
                    }
                    Merge.DECORATIONS -> {
                        for (decoration in TextDecorations.values) {
                            val state = style.decoration(decoration)
                            if (state != Tristate.UNDEFINED && strategy.mergeDecoration(this, decoration)) {
                                decoration(decoration, state)
                            }
                        }
                    }
                    Merge.ACTIONS -> {
                        val clickAction = style.clickAction
                        if (clickAction != null && strategy.mergeClickAction(this, clickAction)) {
                            clickAction(clickAction)
                        }

                        val hoverAction = style.hoverAction
                        if (hoverAction != null && strategy.mergeHoverAction(this, hoverAction)) {
                            hoverAction(hoverAction)
                        }

                        val shiftClickAction = style.shiftClickAction
                        if (shiftClickAction != null && strategy.mergeShiftClickAction(this, shiftClickAction)) {
                            shiftClickAction(shiftClickAction)
                        }
                    }
                    Merge.FONT -> {
                        // TODO
                    }
                }
            }

            return this
        }

        fun apply(style: BuilderApplicable): Builder = apply {
            style.apply(this)
        }

        override fun from(value: TextStyle): Builder = apply {
            color = value.color
            obfuscated = value.obfuscated
            bold = value.bold
            strikethrough = value.strikethrough
            underlined = value.underlined
            italic = value.italic
            clickAction = value.clickAction
            hoverAction = value.hoverAction
            shiftClickAction = value.shiftClickAction
        }

        override fun reset(): Builder = apply {
            color = null
            obfuscated = Tristate.UNDEFINED
            bold = Tristate.UNDEFINED
            strikethrough = Tristate.UNDEFINED
            underlined = Tristate.UNDEFINED
            italic = Tristate.UNDEFINED
            clickAction = null
            hoverAction = null
            shiftClickAction = null
        }

        interface Provider {
            fun provide(): Builder
        }
    }

    interface BuilderApplicable : TextBuilderApplicable {
        fun apply(builder: Builder)

        override fun apply(builder: TextBuilder<*, *>) {
            builder.style {
                apply(it)
            }
        }
    }

    enum class Merge {
        COLOR,
        DECORATIONS,
        ACTIONS,
        FONT;

        companion object {
            @JvmField
            val ALL = of(*values())

            @JvmField
            val COLOR_AND_DECORATIONS = of(COLOR, DECORATIONS)

            @JvmStatic
            fun of(vararg merges: Merge): Set<Merge> =
                if (merges.isEmpty()) emptySet() else EnumSet.copyOf(merges.toList())

            @JvmStatic
            fun hasAll(merges: Iterable<Merge>): Boolean = ALL.all { merges.contains(it) }
        }

        enum class Strategy {
            ALWAYS {
                override fun mergeColor(target: Builder, color: TextColor): Boolean = true
                override fun mergeDecoration(target: Builder, decoration: TextDecoration): Boolean = true
                override fun mergeClickAction(target: Builder, clickAction: ClickAction<*>): Boolean = true
                override fun mergeHoverAction(target: Builder, clickAction: HoverAction<*>): Boolean = true
                override fun mergeShiftClickAction(target: Builder, clickAction: ShiftClickAction<*>): Boolean = true
            },
            NEVER {
                override fun mergeColor(target: Builder, color: TextColor): Boolean = false
                override fun mergeDecoration(target: Builder, decoration: TextDecoration): Boolean = false
                override fun mergeClickAction(target: Builder, clickAction: ClickAction<*>): Boolean = false
                override fun mergeHoverAction(target: Builder, clickAction: HoverAction<*>): Boolean = false
                override fun mergeShiftClickAction(target: Builder, clickAction: ShiftClickAction<*>): Boolean = false
            },
            IF_ABSENT_ON_TARGET {
                override fun mergeColor(target: Builder, color: TextColor): Boolean = target.color == null
                override fun mergeDecoration(target: Builder, decoration: TextDecoration): Boolean = when (decoration) {
                    TextDecorations.OBFUSCATED -> target.obfuscated == Tristate.UNDEFINED
                    TextDecorations.BOLD -> target.bold == Tristate.UNDEFINED
                    TextDecorations.STRIKETHROUGH -> target.strikethrough == Tristate.UNDEFINED
                    TextDecorations.UNDERLINED -> target.underlined == Tristate.UNDEFINED
                    TextDecorations.ITALIC -> target.italic == Tristate.UNDEFINED
                    else -> throw IllegalArgumentException(decoration.toString())
                }

                override fun mergeClickAction(target: Builder, clickAction: ClickAction<*>): Boolean =
                    target.clickAction == null

                override fun mergeHoverAction(target: Builder, clickAction: HoverAction<*>): Boolean =
                    target.hoverAction == null

                override fun mergeShiftClickAction(target: Builder, clickAction: ShiftClickAction<*>): Boolean =
                    target.shiftClickAction == null
            },
            IF_NOT_ABSENT_ON_TARGET {
                override fun mergeColor(target: Builder, color: TextColor): Boolean = target.color != null
                override fun mergeDecoration(target: Builder, decoration: TextDecoration): Boolean = when (decoration) {
                    TextDecorations.OBFUSCATED -> target.obfuscated != Tristate.UNDEFINED
                    TextDecorations.BOLD -> target.bold != Tristate.UNDEFINED
                    TextDecorations.STRIKETHROUGH -> target.strikethrough != Tristate.UNDEFINED
                    TextDecorations.UNDERLINED -> target.underlined != Tristate.UNDEFINED
                    TextDecorations.ITALIC -> target.italic != Tristate.UNDEFINED
                    else -> throw IllegalArgumentException(decoration.toString())
                }

                override fun mergeClickAction(target: Builder, clickAction: ClickAction<*>): Boolean =
                    target.clickAction != null

                override fun mergeHoverAction(target: Builder, clickAction: HoverAction<*>): Boolean =
                    target.hoverAction != null

                override fun mergeShiftClickAction(target: Builder, clickAction: ShiftClickAction<*>): Boolean =
                    target.shiftClickAction != null
            };

            abstract fun mergeColor(target: Builder, color: TextColor): Boolean
            abstract fun mergeDecoration(target: Builder, decoration: TextDecoration): Boolean
            abstract fun mergeClickAction(target: Builder, clickAction: ClickAction<*>): Boolean
            abstract fun mergeHoverAction(target: Builder, clickAction: HoverAction<*>): Boolean
            abstract fun mergeShiftClickAction(target: Builder, clickAction: ShiftClickAction<*>): Boolean
        }
    }

    companion object {
        private val builderProvider = loadService<Builder.Provider>()

        @JvmStatic
        fun builder(): Builder = builderProvider.provide()

        @JvmStatic
        fun empty(): TextStyle = builder().build()

        @JvmStatic
        fun of(vararg styles: BuilderApplicable): TextStyle = of(styles.asIterable())

        @JvmStatic
        fun of(styles: Iterable<BuilderApplicable>): TextStyle {
            if (!styles.iterator().hasNext()) return empty()
            val builder = builder()
            styles.forEach {
                it.apply(builder)
            }
            return builder.build()
        }

        @JvmStatic
        fun build(consumer: Consumer<Builder>): TextStyle = Buildable.build(builder(), consumer)
    }
}

fun TextStyle(vararg applicable: TextStyle.BuilderApplicable): TextStyle = TextStyle.of(*applicable)

fun TextStyle(builder: TextStyle.Builder.() -> Unit): TextStyle = TextStyle.build(builder)
