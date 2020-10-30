package com.karbonpowered.protocol.java.c2s.game

import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.catalog.NamespacedKey
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MagicValues
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.protocol.java.data.Item
import com.karbonpowered.protocol.java.util.readItem
import com.karbonpowered.protocol.java.util.writeItem

data class GameContainerActionC2SPacket
@JvmOverloads
constructor(
    val syncId: Int = 0,
    val actionId: Int = 0,
    val slot: Int = 0,
    val clickedItem: Item? = null,
    val action: ContainerAction = ContainerAction.CLICK_ITEM,
    val param: ContainerActionParam = ContainerActionParam.ClickItem.LEFT_CLICK
) : MinecraftPacket() {
    enum class ContainerAction : CatalogType {
        CLICK_ITEM,
        SHIFT_CLICK_ITEM,
        MOVE_TO_HOTBAR_SLOT,
        CREATIVE_GRAB_MAX_STACK,
        DROP_ITEM,
        SPREAD_ITEM,
        FILL_STACK;

        override val key: NamespacedKey
            get() = NamespacedKey.minecraft(name.toLowerCase())
    }

    interface ContainerActionParam : CatalogType {
        val name: String

        override val key: NamespacedKey
            get() = NamespacedKey.minecraft(name.toLowerCase())

        enum class SpreadItem : ContainerActionParam {
            LEFT_MOUSE_BEGIN_DRAG,
            LEFT_MOUSE_ADD_SLOT,
            LEFT_MOUSE_END_DRAG,
            RIGHT_MOUSE_BEGIN_DRAG,
            RIGHT_MOUSE_ADD_SLOT,
            RIGHT_MOUSE_END_DRAG,
            MIDDLE_MOUSE_BEGIN_DRAG,
            MIDDLE_MOUSE_ADD_SLOT,
            MIDDLE_MOUSE_END_DRAG
        }

        enum class ShiftClick : ContainerActionParam {
            LEFT_CLICK,
            RIGHT_CLICK
        }

        enum class MoveToHotbar : ContainerActionParam {
            SLOT_1,
            SLOT_2,
            SLOT_3,
            SLOT_4,
            SLOT_5,
            SLOT_6,
            SLOT_7,
            SLOT_8,
            SLOT_9
        }

        enum class FillStack : ContainerActionParam {
            FILL
        }

        enum class DropItem : ContainerActionParam {
            DROP_FROM_SELECTED,
            DROP_SELECTED_STACK,
            LEFT_CLICK_OUTSIDE_NOT_HOLDING,
            RIGHT_CLICK_OUTSIDE_NOT_HOLDING
        }

        enum class CreativeGrab : ContainerActionParam {
            GRAB
        }

        enum class ClickItem : ContainerActionParam {
            LEFT_CLICK,
            RIGHT_CLICK
        }
    }

    object Codec : com.karbonpowered.network.codec.Codec<GameContainerActionC2SPacket> {
        override fun decode(buffer: MessageBuf): GameContainerActionC2SPacket {
            val containerId = buffer.readUnsignedByte().toInt()
            val slot = buffer.readShort().toInt()
            val paramValue = buffer.readByte().toInt()
            val actionId = buffer.readShort().toInt()
            val action = buffer.readContainerAction()
            val clickedItem = buffer.readItem()
            val param = when (action) {
                ContainerAction.CLICK_ITEM ->
                    MagicValues.key(ContainerActionParam.ClickItem::class, paramValue)
                ContainerAction.SHIFT_CLICK_ITEM ->
                    MagicValues.key(ContainerActionParam.ShiftClick::class, paramValue)
                ContainerAction.MOVE_TO_HOTBAR_SLOT ->
                    MagicValues.key(ContainerActionParam.MoveToHotbar::class, paramValue)
                ContainerAction.CREATIVE_GRAB_MAX_STACK ->
                    MagicValues.key(ContainerActionParam.CreativeGrab::class, paramValue)
                ContainerAction.DROP_ITEM ->
                    MagicValues.key(ContainerActionParam.DropItem::class, paramValue + if (slot != -999) 2 else 0)
                ContainerAction.SPREAD_ITEM ->
                    MagicValues.key(ContainerActionParam.SpreadItem::class, paramValue)
                ContainerAction.FILL_STACK ->
                    MagicValues.key(ContainerActionParam.FillStack::class, paramValue)
            }
            return GameContainerActionC2SPacket(containerId, actionId, slot, clickedItem, action, param)
        }

        override fun encode(buffer: MessageBuf, message: GameContainerActionC2SPacket): MessageBuf {
            buffer.writeByte(message.syncId)
            buffer.writeShort(message.slot)
            var param = MagicValues.value(Int::class, message.param)
            if (message.action == ContainerAction.DROP_ITEM) {
                param %= 2
            }
            buffer.writeByte(param)
            buffer.writeShort(message.actionId)
            val action = MagicValues.value(Int::class, message.action)
            buffer.writeByte(action)
            buffer.writeItem(message.clickedItem)
            return buffer
        }

        private fun MessageBuf.readContainerAction() = when (val id = readByte().toInt()) {
            0 -> ContainerAction.CLICK_ITEM
            1 -> ContainerAction.SHIFT_CLICK_ITEM
            2 -> ContainerAction.MOVE_TO_HOTBAR_SLOT
            3 -> ContainerAction.CREATIVE_GRAB_MAX_STACK
            4 -> ContainerAction.DROP_ITEM
            5 -> ContainerAction.SPREAD_ITEM
            6 -> ContainerAction.FILL_STACK
            else -> throw IllegalStateException("Unknown container action: $id")
        }

        private fun MessageBuf.writeContainerAction(containerAction: ContainerAction) = apply {
            val id = when (containerAction) {
                ContainerAction.CLICK_ITEM -> 1
                ContainerAction.SHIFT_CLICK_ITEM -> 2
                ContainerAction.MOVE_TO_HOTBAR_SLOT -> 3
                ContainerAction.CREATIVE_GRAB_MAX_STACK -> 4
                ContainerAction.DROP_ITEM -> 5
                ContainerAction.SPREAD_ITEM -> 6
                ContainerAction.FILL_STACK -> 7
            }
            writeByte(id)
        }
    }
}