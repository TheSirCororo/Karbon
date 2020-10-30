package com.karbonpowered.protocol.java.s2c.query

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.karbonpowered.api.MinecraftVersion
import com.karbonpowered.api.network.query.Favicon
import com.karbonpowered.api.network.query.QueryResponse
import com.karbonpowered.api.profile.GameProfile
import com.karbonpowered.network.message.MessageBuf
import com.karbonpowered.protocol.java.MinecraftPacket
import com.karbonpowered.text.Text
import com.karbonpowered.text.serializer.TextSerializers
import java.util.*

data class QueryResponseS2CPacket(
    val response: QueryResponse
) : MinecraftPacket() {
    object Codec : com.karbonpowered.network.codec.Codec<QueryResponseS2CPacket> {
        val gson = Gson()

        override fun decode(buffer: MessageBuf): QueryResponseS2CPacket {
            val responseRaw = buffer.readString()
            val json = gson.fromJson(responseRaw, JsonObject::class.java)
            val jsonVersion = json.get("version").asJsonObject
            val versionName = jsonVersion.get("name").asString
            val versionProtocol = jsonVersion.get("protocol").asInt
            val versionInfo = object : MinecraftVersion {
                override val isLegacy: Boolean = false
                override val name: String = versionName
                override val protocolVersion: Int = versionProtocol

                override fun compareTo(other: MinecraftVersion): Int =
                    protocolVersion.compareTo(other.protocolVersion)
            }
            val jsonPlayers = json.get("players").asJsonObject
            val playersMax = jsonPlayers.get("max").asInt
            val playersOnline = jsonPlayers.get("online").asInt
            val playersProfiles = if (jsonPlayers.has("sample")) {
                val jsonSample = jsonPlayers.get("sample").asJsonArray
                jsonSample.map { jsonProfile ->
                    jsonProfile as JsonObject
                    val profileName = jsonProfile.get("name").asString
                    val profileId = jsonProfile.get("id").asString
                    val profileUniqueId = UUID.fromString(profileId)
                    GameProfile.of(profileUniqueId, profileName)
                }
            } else emptyList()
            val players = object : QueryResponse.Players {
                override val max: Int = playersMax
                override val online: Int = playersOnline
                override val profiles: List<GameProfile> = playersProfiles
            }
            val rawDescription = json.get("description").asString
            val description = TextSerializers.JSON.deserialize(rawDescription)

            val favicon = if (json.has("favicon")) {
                val rawFavicon = json.get("favicon").asString
                if (rawFavicon.isNotEmpty()) {
                    Favicon.load(rawFavicon)
                } else null
            } else null

            val queryResponse = object : QueryResponse {
                override val description: Text = description
                override val favicon: Favicon? = favicon
                override val players: QueryResponse.Players? = players
                override val version: MinecraftVersion = versionInfo
            }

            return QueryResponseS2CPacket(queryResponse)
        }

        override fun encode(buffer: MessageBuf, message: QueryResponseS2CPacket): MessageBuf {
            val response = message.response
            val version = response.version
            val players = response.players
            val description = response.description
            val favicon = response.favicon
            val jsonVersion = JsonObject()
            jsonVersion.addProperty("name", version.name)
            jsonVersion.addProperty("protocol", version.protocolVersion)
            val jsonPlayers = if (players != null) {
                val json = JsonObject()
                json.addProperty("max", players.max)
                json.addProperty("online", players.online)
                val profiles = JsonArray()
                players.profiles.forEach {
                    val jsonProfile = JsonObject()
                    jsonProfile.addProperty("name", it.name)
                    jsonProfile.addProperty("id", it.uniqueId.toString())
                    profiles.add(jsonProfile)
                }
                json.add("sample", profiles)
                json
            } else null
            val jsonDescription = gson.fromJson(TextSerializers.JSON.serialize(description), JsonObject::class.java)
            val rawFavicon = favicon?.toString()

            val jsonResponse = JsonObject()
            jsonResponse.add("version", jsonVersion)
            jsonPlayers?.also { jsonResponse.add("players", it) }
            jsonResponse.add("description", jsonDescription)
            rawFavicon?.also { jsonResponse.addProperty("favicon", it) }
            buffer.writeString(jsonResponse.toString())
            return buffer
        }
    }
}