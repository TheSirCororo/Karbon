package com.karbonpowered.text

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.builder.BuildableText
import com.karbonpowered.text.builder.TextBuilder
import com.karbonpowered.text.format.TextColor

interface TranslatableText : BuildableText<TranslatableText, TranslatableText.Builder>, ScopedText<TranslatableText> {
    val key: String
    val args: Collection<Text>

    fun key(key: String): TranslatableText = toBuilder().key(key).build()

    fun args(vararg args: TextRepresentable): TranslatableText = args(args.asIterable())

    fun args(args: Iterable<TextRepresentable>): TranslatableText = toBuilder().args(args).build()

    override fun toBuilder(): Builder = builder().from(this)

    interface Factory {
        fun builder(): Builder
    }

    interface Builder : TextBuilder<TranslatableText, Builder> {
        var key: String?
        var args: MutableCollection<TextRepresentable>

        fun key(key: String): Builder = apply {
            this.key = key
        }

        fun args(vararg args: TextRepresentable): Builder = args(args.asIterable())

        fun args(args: Iterable<TextRepresentable>): Builder = apply {
            this.args.addAll(args)
        }

        override fun from(value: TranslatableText): Builder = apply {
            key = value.key
            super.from(value)
        }

        override fun reset(): Builder = apply {
            key = null
            super.reset()
        }
    }

    companion object {
        private val factory = loadService<Factory>()

        fun builder(): Builder = factory.builder()

        fun of(key: String, vararg args: TextRepresentable): TranslatableText = of(key, args.asIterable())

        fun of(key: String, args: Iterable<TextRepresentable>): TranslatableText = of(key, null, args)

        fun of(key: String, color: TextColor?, vararg args: TextRepresentable): TranslatableText =
            of(key, color, args.asIterable())

        fun of(key: String, color: TextColor?, args: Iterable<TextRepresentable>): TranslatableText =
            builder().key(key).color(color).args(args).build()
    }
}

fun TranslatableText(key: String, vararg args: TextRepresentable): TranslatableText = TranslatableText.of(key, *args)
fun TranslatableText(key: String, args: Iterable<TextRepresentable>): TranslatableText = TranslatableText.of(key, args)
fun TranslatableText(key: String, color: TextColor?, vararg args: TextRepresentable): TranslatableText =
    TranslatableText.of(key, color, *args)

fun TranslatableText(key: String, color: TextColor?, args: Iterable<TextRepresentable>): TranslatableText =
    TranslatableText.of(key, color, args)

fun TranslatableText(key: String, builder: TranslatableText.Builder.() -> Unit = {}): TranslatableText =
    TranslatableText.builder().key(key).apply(builder).build()