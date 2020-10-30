package com.karbonpowered.api.network.query

import com.karbonpowered.commons.lang.loadService
import java.awt.image.BufferedImage
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

interface Favicon {
    val image: BufferedImage

    override fun toString(): String

    interface Factory {
        fun load(raw: String): Favicon

        fun load(path: Path): Favicon

        fun load(url: URL): Favicon

        fun load(inputStream: InputStream): Favicon

        fun load(bufferedImage: BufferedImage): Favicon
    }

    companion object {
        fun load(raw: String): Favicon = loadService<Factory>().load(raw)

        fun load(path: Path): Favicon = loadService<Factory>().load(path)

        fun load(url: URL): Favicon = loadService<Factory>().load(url)

        fun load(inputStream: InputStream): Favicon = loadService<Factory>().load(inputStream)

        fun load(bufferedImage: BufferedImage): Favicon = loadService<Factory>().load(bufferedImage)
    }
}