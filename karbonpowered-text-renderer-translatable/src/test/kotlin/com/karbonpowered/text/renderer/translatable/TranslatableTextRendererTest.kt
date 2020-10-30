package com.karbonpowered.text.renderer.translatable

import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.Text
import com.karbonpowered.text.TranslatableText
import com.karbonpowered.text.format.TextColors
import com.karbonpowered.translation.TranslationRegistry
import com.karbonpowered.translation.locale.Locales
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.text.MessageFormat

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TranslatableTextRendererTest {
    init {
        TranslationRegistry.register("what", Locales.EN_US, MessageFormat("What!?"))
        TranslationRegistry.register("what", Locales.RU_RU, MessageFormat("Что!?"))

        TranslationRegistry.register("cat", Locales.EN_US, MessageFormat("Cat?"))
        TranslationRegistry.register("cat", Locales.RU_RU, MessageFormat("Кошка?"))

        TranslationRegistry.register("dog", Locales.EN_US, MessageFormat("Dog!"))
        TranslationRegistry.register("dog", Locales.RU_RU, MessageFormat("Собака!"))

        TranslationRegistry.register("test.message", Locales.EN_US, MessageFormat("Test message with {0} and {1}!"))
        TranslationRegistry.register("test.message", Locales.RU_RU, MessageFormat("Тестовое сообщение с {0} и {1}!"))
    }

    @Test
    fun `Test render simple`() {
        val expectedEn = LiteralText.of("What!?", TextColors.YELLOW)
        val expectedRu = LiteralText.of("Что!?", TextColors.YELLOW)

        val text = TranslatableText.of("what", TextColors.YELLOW)

        val renderedEn = TranslatableTextRenderer.render(text, Locales.EN_US)
        val renderedRu = TranslatableTextRenderer.render(text, Locales.RU_RU)

        assertEquals(expectedEn, renderedEn)
        assertEquals(expectedRu, renderedRu)
    }

    @Test
    fun `Test render complex`() {
        val expectedEn = Text(
            Text(TextColors.YELLOW, "Test message with "),
            Text("XjCyan1de"),
            Text(TextColors.YELLOW, " and "),
            Text("rqbik"),
            Text(TextColors.YELLOW, "!")
        )

        val expectedRu = Text(
            Text(TextColors.YELLOW, "Тестовое сообщение с "),
            Text("XjCyan1de"),
            Text(TextColors.YELLOW, " и "),
            Text("rqbik"),
            Text(TextColors.YELLOW, "!")
        )

        val text = TranslatableText.of(
            "test.message", TextColors.YELLOW,
            LiteralText.of("XjCyan1de"),
            LiteralText.of("rqbik")
        )

        val renderedEn = TranslatableTextRenderer.render(text, Locales.EN_US)
        val renderedRu = TranslatableTextRenderer.render(text, Locales.RU_RU)

        assertEquals(expectedEn, renderedEn)
        assertEquals(expectedRu, renderedRu)
    }

    @Test
    fun `Render very complex`() {
        val expected = LiteralText.builder()
            .color(TextColors.YELLOW)
            .append("What!?")
            .append(
                LiteralText.builder()
                    .append("Test message with ")
                    .append("Cat?")
                    .append(" and ")
                    .append(LiteralText.of("Dog!", TextColors.BLUE))
                    .append("!")
                    .build()
            )
            .build()

        val text = LiteralText.builder()
            .color(TextColors.YELLOW)
            .append(TranslatableText.of("what"))
            .append(
                TranslatableText.of(
                    "test.message",
                    TranslatableText.of("cat"),
                    TranslatableText.of("dog", TextColors.BLUE)
                )
            )
            .build()

        val rendered = TranslatableTextRenderer.render(text, Locales.EN_US)

        assertEquals(expected, rendered)
    }
}