package com.karbonpowered.protocol.java.protocol

import com.karbonpowered.protocol.java.MinecraftProtocol
import com.karbonpowered.protocol.java.c2s.game.*
import com.karbonpowered.protocol.java.s2c.game.*

open class GameProtocol(isServerSide: Boolean) : MinecraftProtocol("game", isServerSide, 0) {
    init {
        registerPacket(false, GameChatMessageS2CPacket::class, GameChatMessageS2CPacket.Codec_1_16::class, 0x0E)
        registerPacket(false, GameCloseContainerS2CPacket::class, GameCloseContainerS2CPacket.Codec::class, 0x12)
        registerPacket(false, GameContainerItemsS2CPacket::class, GameContainerItemsS2CPacket.Codec::class, 0x13)
        registerPacket(false, GameCustomPayloadS2CPacket::class, GameCustomPayloadS2CPacket.Codec::class, 0x17)
        registerPacket(false, GameKeepAliveS2CPacket::class, GameKeepAliveS2CPacket.Codec::class, 0x1F)
        registerPacket(false, GameJoinS2CPacket::class, GameJoinS2CPacket.Codec_1_16_2::class, 0x24)
        registerPacket(false, GameOpenContainerS2CPacket::class, GameOpenContainerS2CPacket.Codec::class, 0x2D)
        registerPacket(
            false,
            GamePlayerPositionRotationS2CPacket::class,
            GamePlayerPositionRotationS2CPacket.Codec::class,
            0x34
        )
        registerPacket(false, GameChunkDataS2CPacket::class, GameChunkDataS2CPacket.Codec_1_16::class, 0x20)
        registerPacket(
            false,
            GameUpdateViewPositionS2CPacket::class,
            GameUpdateViewPositionS2CPacket.Codec::class,
            0x40
        )
        registerPacket(false, GameTitleS2CPacket::class, GameTitleS2CPacket.Codec_1_16::class, 0x4F)

        registerPacket(true, GameTeleportConfirmC2SPacket::class, GameTeleportConfirmC2SPacket.Codec::class, 0x00)
        registerPacket(
            true,
            GameBlockBinaryTagRequestC2SPacket::class,
            GameBlockBinaryTagRequestC2SPacket.Codec_1_14::class,
            0x01
        )
        registerPacket(true, GameSetDifficultyC2SPacket::class, GameSetDifficultyC2SPacket.Codec::class, 0x02)
        registerPacket(true, GameChatC2SPacket::class, GameChatC2SPacket.Codec::class, 0x03)
        registerPacket(true, GameRequestC2SPacket::class, GameRequestC2SPacket.Codec::class, 0x04)
        registerPacket(true, GameClientSettingsC2SPacket::class, GameClientSettingsC2SPacket.Codec_1_9::class, 0x05)
        registerPacket(true, GameTabCompleteC2SPacket::class, GameTabCompleteC2SPacket.Codec_1_14::class, 0x06)
        registerPacket(true, GameConfirmTransactionC2SPacket::class, GameConfirmTransactionC2SPacket.Codec::class, 0x07)
        registerPacket(
            true,
            GameContainerClickButtonC2SPacket::class,
            GameContainerClickButtonC2SPacket.Codec::class,
            0x08
        )
        registerPacket(true, GameContainerActionC2SPacket::class, GameContainerActionC2SPacket.Codec::class, 0x09)
        registerPacket(true, GameCloseContainerC2SPacket::class, GameCloseContainerC2SPacket.Codec::class, 0x0A)
        registerPacket(true, GameCustomPayloadC2SPacket::class, GameCustomPayloadC2SPacket.Codec::class, 0x0B)
        registerPacket(true, GameEditBookC2SPacket::class, GameEditBookC2SPacket.Codec_1_13::class, 0x0C)
        registerPacket(
            true,
            GameEntityNbtRequestPacketC2SPacket::class,
            GameEntityNbtRequestPacketC2SPacket.Codec::class,
            0x0D
        )
        registerPacket(
            true,
            GamePlayerInteractEntityC2SPacket::class,
            GamePlayerInteractEntityC2SPacket.Codec1_9::class,
            0x0E
        )
        registerPacket(true, GameGenerateStructuresC2SPacket::class, GameGenerateStructuresC2SPacket.Codec::class, 0x0F)
        registerPacket(true, GameKeepAliveC2SPacket::class, GameKeepAliveC2SPacket.Codec_1_12_2::class, 0x10)
        registerPacket(true, GameLockDifficultyC2SPacket::class, GameLockDifficultyC2SPacket.Codec::class, 0x11)
        registerPacket(true, GamePlayerPositionC2SPacket::class, GamePlayerPositionC2SPacket.Codec1_8::class, 0x12)
        registerPacket(
            true,
            GamePlayerPositionRotationC2SPacket::class,
            GamePlayerPositionRotationC2SPacket.Codec::class,
            0x13
        )
        registerPacket(true, GamePlayerRotationC2SPacket::class, GamePlayerRotationC2SPacket.Codec::class, 0x14)
        registerPacket(true, GamePlayerMovementC2SPacket::class, GamePlayerMovementC2SPacket.Codec::class, 0x15)
        registerPacket(true, GamePlayerVehicleMoveC2SPacket::class, GamePlayerVehicleMoveC2SPacket.Codec::class, 0x16)
        registerPacket(true, GameSteerBoatC2SPacket::class, GameSteerBoatC2SPacket.Codec::class, 0x17)
        registerPacket(true, GameMoveItemToHotbarC2SPacket::class, GameMoveItemToHotbarC2SPacket.Codec::class, 0x18)
        registerPacket(
            true,
            GamePrepareCraftingGridC2SPacket::class,
            GamePrepareCraftingGridC2SPacket.Codec::class,
            0x19
        )
        registerPacket(true, GamePlayerAbilitiesC2SPacket::class, GamePlayerAbilitiesC2SPacket.Codec::class, 0x1A)
        registerPacket(true, GamePlayerActionC2SPacket::class, GamePlayerActionC2SPacket.Codec::class, 0x1B)
        registerPacket(true, GamePlayerStateC2SPacket::class, GamePlayerStateC2SPacket.Codec::class, 0x1C)

        registerPacket(
            true,
            GamePlayerChangeHeldItemC2SPacket::class,
            GamePlayerChangeHeldItemC2SPacket.Codec::class,
            0x25
        )

        registerPacket(true, GamePlayerSwingArmC2SPacket::class, GamePlayerSwingArmC2SPacket.Codec::class, 0x2C)

        registerPacket(true, GamePlayerUseItemC2SPacket::class, GamePlayerUseItemC2SPacket.Codec::class, 0x2F)
    }
}