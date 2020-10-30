package com.karbonpowered.protocol.java.util

import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.data.Item
import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.TextSerializers

fun MessageBuf.readText(): Text = TextSerializers.JSON.deserialize(readString())

fun MessageBuf.writeText(text: Text): MessageBuf = apply {
    writeString(TextSerializers.JSON.serialize(text))
}

fun MessageBuf.readItem(): Item? = Item.read(this)

fun MessageBuf.writeItem(item: Item?) = apply {
    Item.write(this, item)
}

fun Int.getBitValue(bitmask: Int): Boolean = this and bitmask == bitmask

fun Int.setBitValue(value: Boolean, bitmask: Int): Int =
    if (value) {
        this or bitmask
    } else {
        this and bitmask.inv()
    }