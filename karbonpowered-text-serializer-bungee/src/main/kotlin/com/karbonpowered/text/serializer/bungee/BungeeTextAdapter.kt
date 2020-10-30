@file:JvmName("BungeeTextAdapter")

package com.karbonpowered.text.serializer.bungee

import com.karbonpowered.text.Text
import com.karbonpowered.text.format.NamedTextColor
import com.karbonpowered.text.format.TextColor
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent

fun ChatColor.asKarbon(): TextColor = TextColor(name)!!
fun TextColor.asBungee(): ChatColor = ChatColor.of(if (this is NamedTextColor) name else asHexString())

fun Array<out BaseComponent>.asKarbon(): Text = BungeeTextSerializer.deserialize(this)
fun BaseComponent.asKarbon(): Text = BungeeTextSerializer.deserialize(this)
fun Text.asBungee(): Array<BaseComponent> = BungeeTextSerializer.serialize(this)