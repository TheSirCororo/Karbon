package com.github.karbonpowered.karbon.network.protocol.server

import com.github.karbonpowered.api.catalog.CatalogKey
import com.github.karbonpowered.api.entity.living.humanoid.player.gamemode.GameModes
import com.github.karbonpowered.api.nbt.CompoundBinaryTag
import com.github.karbonpowered.api.nbt.ListBinaryTag
import com.github.karbonpowered.api.nbt.set
import com.github.karbonpowered.api.nbt.toBinaryTag
import com.github.karbonpowered.api.profile.GameProfile
import com.github.karbonpowered.protocol.java.ProtocolState
import com.github.karbonpowered.protocol.java.c2s.login.LoginStartC2SPacket
import com.github.karbonpowered.protocol.java.protocol.LoginProtocol
import com.github.karbonpowered.protocol.java.s2c.game.GameJoinS2CPacket
import com.github.karbonpowered.protocol.java.s2c.game.GamePlayerPositionRotationS2CPacket
import com.github.karbonpowered.protocol.java.s2c.login.LoginSuccessS2CPacket
import java.util.*

object LoginServerProtocol : LoginProtocol(true) {
    init {
        registerHandler(LoginStartC2SPacket::class) { session, packet ->
            println("[Join] $session: $packet")
            session.send(LoginSuccessS2CPacket(GameProfile.of(UUID.randomUUID(), packet.username)))
            session.protocolState = ProtocolState.GAME
            session.protocol = GameServerProtocol
            session.send(GameJoinS2CPacket(
                    0,
                    false,
                    GameModes.SPECTATOR,
                    GameModes.SPECTATOR,
                    listOf(CatalogKey.minecraft("world")),
                    CompoundBinaryTag {
                        this["dimension"] = ListBinaryTag {
                            add(CompoundBinaryTag {
                                this["name"] = CatalogKey.minecraft("the_end")
                                this["natural"] = true
                                this["natural"] = true
                                this["ambient_light"] = 1.0f
                                this["has_ceiling"] = false
                                this["has_skylight"] = true
                                this["fixed_time"] = 0L
                                this["shrunk"] = false
                                this["ultrawarm"] = false
                                this["has_raids"] = false
                                this["respawn_anchor_works"] = false
                                this["bed_works"] = false
                                this["piglin_safe"] = false
                                this["logical_height"] = 256
                                this["infiniburn"] = CatalogKey.minecraft("netherrack")
                            })
                        }
                    },
                    CatalogKey.minecraft("the_end"),
                    CatalogKey.minecraft("world"),
                    0,
                    100,
                    2,
                    true,
                    false,
                    true,
                    true
            ))
            session.send(GamePlayerPositionRotationS2CPacket(0.0, 100.0, 0.0, 0f, 0f, 0))
        }
    }
}

private fun a() {
    val old = CompoundBinaryTag {
        this["dimension"] = ListBinaryTag {
            add(CompoundBinaryTag {
                this["name"] = CatalogKey.minecraft("the_end")
                this["natural"] = true
                this["natural"] = true
                this["ambient_light"] = 1.0f
                this["has_ceiling"] = false
                this["has_skylight"] = true
                this["fixed_time"] = 0L
                this["shrunk"] = false
                this["ultrawarm"] = false
                this["has_raids"] = false
                this["respawn_anchor_works"] = false
                this["bed_works"] = false
                this["piglin_safe"] = false
                this["logical_height"] = 256
                this["infiniburn"] = CatalogKey.minecraft("netherrack")
            })
        }
    }

    val new = CompoundBinaryTag(
            "dimension" to ListBinaryTag(
                    CompoundBinaryTag(
                            "name" to CatalogKey.minecraft("the_end").toBinaryTag(),
                            "natural" to true.toBinaryTag(),
                            "natural" to true.toBinaryTag(),
                            "ambient_light" to 1.0f.toBinaryTag(),
                            "has_ceiling" to false.toBinaryTag(),
                            "has_skylight" to true.toBinaryTag(),
                            "fixed_time" to 0L.toBinaryTag(),
                            "shrunk" to false.toBinaryTag(),
                            "ultrawarm" to false.toBinaryTag(),
                            "has_raids" to false.toBinaryTag(),
                            "respawn_anchor_works" to false.toBinaryTag(),
                            "bed_works" to false.toBinaryTag(),
                            "piglin_safe" to false.toBinaryTag(),
                            "logical_height" to 256.toBinaryTag(),
                            "infiniburn" to CatalogKey.minecraft("netherrack").toBinaryTag()
                    )
            )
    )
}