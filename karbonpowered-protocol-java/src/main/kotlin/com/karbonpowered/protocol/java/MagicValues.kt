package com.karbonpowered.protocol.java

import com.karbonpowered.api.chat.ChatVisibilities
import com.karbonpowered.api.chat.MessagePositions
import com.karbonpowered.api.entity.living.humanoid.player.gamemode.GameModes
import com.karbonpowered.api.entity.living.humanoid.player.hand.HandTypes
import com.karbonpowered.api.entity.living.humanoid.player.modelpart.SkinParts
import com.karbonpowered.api.item.inventory.ContainerTypes
import com.karbonpowered.api.world.difficulty.Difficulties
import com.karbonpowered.catalog.CatalogType
import com.karbonpowered.protocol.java.c2s.game.GameContainerActionC2SPacket
import com.karbonpowered.protocol.java.c2s.game.GamePlayerActionC2SPacket
import com.karbonpowered.protocol.java.c2s.game.GamePlayerStateC2SPacket
import com.karbonpowered.protocol.java.c2s.game.GameRequestC2SPacket
import com.karbonpowered.protocol.java.data.BlockFace
import com.karbonpowered.protocol.java.data.InteractAction
import com.karbonpowered.protocol.java.exception.UnmappedKeyException
import com.karbonpowered.protocol.java.exception.UnmappedValueException
import com.karbonpowered.protocol.java.s2c.game.GameTitleS2CPacket
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KClass

object MagicValues {
    private val VALUES = HashMap<CatalogType, ArrayList<Any>>()

    init {
        register(GameModes.SURVIVAL, 0)
        register(GameModes.CREATIVE, 1)
        register(GameModes.ADVENTURE, 2)
        register(GameModes.SPECTATOR, 3)
        register(GameModes.NOT_SET, -1)

        register(Difficulties.PEACEFUL, 0)
        register(Difficulties.EASY, 1)
        register(Difficulties.NORMAL, 2)
        register(Difficulties.HARD, 3)

        register(HandTypes.MAIN_HAND, 0)
        register(HandTypes.OFF_HAND, 1)

        register(InteractAction.INTERACT, 0)
        register(InteractAction.ATTACK, 1)
        register(InteractAction.INTERACT_AT, 2)

        register(BlockFace.DOWN, 0)
        register(BlockFace.UP, 1)
        register(BlockFace.NORTH, 2)
        register(BlockFace.SOUTH, 3)
        register(BlockFace.WEST, 4)
        register(BlockFace.EAST, 5)
        register(BlockFace.SPECIAL, 255)

        register(ChatVisibilities.FULL, 0)
        register(ChatVisibilities.SYSTEM, 1)
        register(ChatVisibilities.HIDDEN, 2)

        register(MessagePositions.CHAT, 0)
        register(MessagePositions.SYSTEM, 1)
        register(MessagePositions.ACTION_BAR, 2)

        register(GameTitleS2CPacket.Action.TITLE, 0)
        register(GameTitleS2CPacket.Action.SUBTITLE, 1)
        register(GameTitleS2CPacket.Action.ACTION_BAR, 2)
        register(GameTitleS2CPacket.Action.TIMES, 3)
        register(GameTitleS2CPacket.Action.CLEAR, 4)
        register(GameTitleS2CPacket.Action.RESET, 5)

        register(SkinParts.CAPE, 0)
        register(SkinParts.JACKET, 1)
        register(SkinParts.LEFT_SLEEVE, 2)
        register(SkinParts.RIGHT_SLEEVE, 3)
        register(SkinParts.RIGHT_SLEEVE, 4)
        register(SkinParts.LEFT_PANTS_LEG, 5)
        register(SkinParts.RIGHT_PANTS_LEG, 6)
        register(SkinParts.HAT, 7)

        register(ContainerTypes.GENERIC_9X1, 0)
        register(ContainerTypes.GENERIC_9X2, 1)
        register(ContainerTypes.GENERIC_9X3, 2)
        register(ContainerTypes.GENERIC_9X4, 3)
        register(ContainerTypes.GENERIC_9X5, 4)
        register(ContainerTypes.GENERIC_9X6, 5)
        register(ContainerTypes.GENERIC_3X3, 6)
        register(ContainerTypes.ANVIL, 7)
        register(ContainerTypes.BEACON, 8)
        register(ContainerTypes.BLAST_FURNACE, 9)
        register(ContainerTypes.BREWING_STAND, 10)
        register(ContainerTypes.CRAFTING, 11)
        register(ContainerTypes.ENCHANTMENT, 12)
        register(ContainerTypes.FURNACE, 13)
        register(ContainerTypes.GRINDSTONE, 14)
        register(ContainerTypes.HOPPER, 15)
        register(ContainerTypes.LECTERN, 16)
        register(ContainerTypes.LOOM, 17)
        register(ContainerTypes.MERCHANT, 18)
        register(ContainerTypes.SHULKER_BOX, 19)
        register(ContainerTypes.SMITHING, 20)
        register(ContainerTypes.SMOKER, 21)
        register(ContainerTypes.CARTOGRAPHY, 22)
        register(ContainerTypes.STONECUTTER, 23)

        register(GameContainerActionC2SPacket.ContainerAction.CLICK_ITEM, 0)
        register(GameContainerActionC2SPacket.ContainerAction.SHIFT_CLICK_ITEM, 1)
        register(GameContainerActionC2SPacket.ContainerAction.MOVE_TO_HOTBAR_SLOT, 2)
        register(GameContainerActionC2SPacket.ContainerAction.CREATIVE_GRAB_MAX_STACK, 3)
        register(GameContainerActionC2SPacket.ContainerAction.DROP_ITEM, 4)
        register(GameContainerActionC2SPacket.ContainerAction.SPREAD_ITEM, 5)
        register(GameContainerActionC2SPacket.ContainerAction.FILL_STACK, 6)

        register(GameContainerActionC2SPacket.ContainerActionParam.ClickItem.LEFT_CLICK, 0)
        register(GameContainerActionC2SPacket.ContainerActionParam.ClickItem.RIGHT_CLICK, 1)

        register(GameContainerActionC2SPacket.ContainerActionParam.ShiftClick.LEFT_CLICK, 0)
        register(GameContainerActionC2SPacket.ContainerActionParam.ShiftClick.RIGHT_CLICK, 1)

        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_1, 0)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_2, 1)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_3, 2)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_4, 3)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_5, 4)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_6, 5)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_7, 6)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_8, 7)
        register(GameContainerActionC2SPacket.ContainerActionParam.MoveToHotbar.SLOT_9, 8)

        register(GameContainerActionC2SPacket.ContainerActionParam.CreativeGrab.GRAB, 2)

        register(GameContainerActionC2SPacket.ContainerActionParam.DropItem.LEFT_CLICK_OUTSIDE_NOT_HOLDING, 0)
        register(GameContainerActionC2SPacket.ContainerActionParam.DropItem.RIGHT_CLICK_OUTSIDE_NOT_HOLDING, 1)
        register(GameContainerActionC2SPacket.ContainerActionParam.DropItem.DROP_FROM_SELECTED, 2)
        register(GameContainerActionC2SPacket.ContainerActionParam.DropItem.DROP_SELECTED_STACK, 3)

        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.LEFT_MOUSE_BEGIN_DRAG, 0)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.LEFT_MOUSE_ADD_SLOT, 1)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.LEFT_MOUSE_END_DRAG, 2)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.RIGHT_MOUSE_BEGIN_DRAG, 4)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.RIGHT_MOUSE_ADD_SLOT, 5)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.RIGHT_MOUSE_END_DRAG, 6)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.MIDDLE_MOUSE_BEGIN_DRAG, 8)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.MIDDLE_MOUSE_ADD_SLOT, 9)
        register(GameContainerActionC2SPacket.ContainerActionParam.SpreadItem.MIDDLE_MOUSE_END_DRAG, 10)

        register(GameContainerActionC2SPacket.ContainerActionParam.FillStack.FILL, 0)

        register(GameRequestC2SPacket.Request.RESPAWN, 0)
        register(GameRequestC2SPacket.Request.STATISTICS, 1)

        register(GamePlayerStateC2SPacket.PlayerState.START_SNEAKING, 0)
        register(GamePlayerStateC2SPacket.PlayerState.STOP_SNEAKING, 1)
        register(GamePlayerStateC2SPacket.PlayerState.LEAVE_BED, 2)
        register(GamePlayerStateC2SPacket.PlayerState.START_SPRINTING, 3)
        register(GamePlayerStateC2SPacket.PlayerState.STOP_SPRINTING, 4)
        register(GamePlayerStateC2SPacket.PlayerState.START_HORSE_JUMP, 5)
        register(GamePlayerStateC2SPacket.PlayerState.STOP_HORSE_JUMP, 6)
        register(GamePlayerStateC2SPacket.PlayerState.OPEN_HORSE_INVENTORY, 7)
        register(GamePlayerStateC2SPacket.PlayerState.START_ELYTRA_FLYING, 8)

        register(GamePlayerActionC2SPacket.PlayerAction.START_DIGGING, 0)
        register(GamePlayerActionC2SPacket.PlayerAction.CANCEL_DIGGING, 1)
        register(GamePlayerActionC2SPacket.PlayerAction.FINISH_DIGGING, 2)
        register(GamePlayerActionC2SPacket.PlayerAction.DROP_ITEM_STACK, 3)
        register(GamePlayerActionC2SPacket.PlayerAction.DROP_ITEM, 4)
        register(GamePlayerActionC2SPacket.PlayerAction.RELEASE_USE_ITEM, 5)
        register(GamePlayerActionC2SPacket.PlayerAction.SWAP_HANDS, 6)
    }

    private fun register(key: CatalogType, value: Any) {
        VALUES.getOrPut(key) { ArrayList() }.add(value)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> key(keyType: Class<T>, value: Any): T {
        for (key in VALUES.keys) {
            if (keyType.isAssignableFrom(key.javaClass)) {
                for (v in VALUES[key] ?: throw UnmappedValueException(value, keyType)) {
                    if (v == value) {
                        return key as T
                    } else if (Number::class.java.isAssignableFrom(v.javaClass) && Number::class.java.isAssignableFrom(v.javaClass)) {
                        val num = v as Number
                        val num2 = value as Number
                        if (num.toDouble() == num2.toDouble()) {
                            return key as T
                        }
                    } else if (String::class.java.isAssignableFrom(v.javaClass) && String::class.java.isAssignableFrom(v.javaClass)) {
                        val str = v as String
                        val str2 = value as String
                        if (str.equals(str2, ignoreCase = true)) {
                            return key as T
                        }
                    }
                }
            }
        }
        throw UnmappedValueException(value, keyType)
    }

    fun <T : Any> key(keyType: KClass<T>, value: Any): T = key(keyType.java, value)
    inline fun <reified T : Any> key(value: Any): T = key(T::class, value)

    @Suppress("UNCHECKED_CAST")
    fun <T> value(valueType: Class<T>, key: CatalogType): T {
        if (VALUES.containsKey(key)) {
            for (value in VALUES[key] ?: throw UnmappedKeyException(key, valueType)) {
                if (valueType.isAssignableFrom(value.javaClass)) {
                    return value as T
                } else if (Number::class.java.isAssignableFrom(value.javaClass)) {
                    return when (valueType) {
                        Byte::class.java -> (value as Number).toByte() as T
                        Short::class.java -> (value as Number).toShort() as T
                        Int::class.java -> (value as Number).toInt() as T
                        Long::class.java -> (value as Number).toLong() as T
                        Float::class.java -> (value as Number).toFloat() as T
                        Double::class.java -> (value as Number).toDouble() as T
                        else -> throw RuntimeException()
                    }
                }
            }
        }
        throw UnmappedKeyException(key, valueType)
    }

    fun <T : Any> value(valueType: KClass<T>, key: CatalogType): T = value(valueType.java, key)
    inline fun <reified T : Any> value(key: CatalogType): T = value(T::class, key)
}