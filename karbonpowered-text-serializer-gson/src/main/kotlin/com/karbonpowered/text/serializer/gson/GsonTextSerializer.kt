package com.karbonpowered.text.serializer.gson

import com.google.gson.GsonBuilder
import com.karbonpowered.text.Text
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextStyle
import com.karbonpowered.text.serializer.JsonTextSerializer

class GsonTextSerializer : JsonTextSerializer {
    val gson = GsonBuilder()
        .registerTypeHierarchyAdapter(Text::class.java, TextSerializer)
        .registerTypeHierarchyAdapter(TextStyle::class.java, TextStyleSerializer)
        .registerTypeHierarchyAdapter(TextColor::class.java, TextColorSerializer.get())
        .create()

    override fun deserialize(input: String): Text = gson.fromJson(input, Text::class.java)

    override fun serialize(text: Text): String = gson.toJson(text)
}