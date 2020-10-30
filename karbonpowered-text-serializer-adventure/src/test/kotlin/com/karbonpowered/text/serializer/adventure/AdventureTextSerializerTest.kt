package com.karbonpowered.text.serializer.adventure

class AdventureTextSerializerTest {
    /* fun createKarbonText() = Text(
             TextColors.AQUA, TextActions.showText(Text("Hello!")), "Test",
             Text.space(),
             Text(TextColors.RED, "with"),
             Text.space(),
             LiteralText.of("Multiple", null, TextDecorations.ITALIC, TextDecorations.UNDERLINED, TextDecorations.BOLD, TextDecorations.STRIKETHROUGH),
             " ",
             Text(TextActions.runCommand("/me Yaya"), "components"),
             TextDecorations.OBFUSCATED, "!"
     )

     fun createAdventureText() = TextComponent.builder()
             .content("Test")
             .color(AdventureNamedTextColor.AQUA)
             .hoverEvent(HoverEvent.showText(TextComponent.of("Hello!")) as HoverEventSource<*>)
             .append(" ")
             .append("with", AdventureNamedTextColor.RED)
             .append(" ")
             .append(TextComponent.of("Multiple", Style.of(AdventureTextDecoration.ITALIC, AdventureTextDecoration.UNDERLINED, AdventureTextDecoration.BOLD, AdventureTextDecoration.STRIKETHROUGH)))
             .append(" ")
             .append(TextComponent.of("components", Style.of(ClickEvent.runCommand("/me Yaya"))))
             .append(TextComponent.of("!", Style.of(AdventureTextDecoration.OBFUSCATED)))
             .build()

     @Test
     fun serialize() {
         val expected = createAdventureText()
         val text = createKarbonText()
         val serialized = text.asAdventure()
         assertEquals(expected, serialized)
     }

     @Test
     fun deserialize() {
         val expected = createKarbonText()
         val component = createAdventureText()
         val deserialized = component.asKarbon()
         assertEquals(expected, deserialized)
     }

     @Test
     fun `serialized and deserialize`() {
         val adventure = createAdventureText()
         val karbon = createKarbonText()
         assertEquals(adventure, adventure.asKarbon().asAdventure())
         assertEquals(karbon, karbon.asAdventure().asKarbon())
     }*/
}