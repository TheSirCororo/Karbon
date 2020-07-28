package com.github.karbonpowered.karbon.network.query

import com.github.karbonpowered.api.network.query.Favicon
import com.github.karbonpowered.api.registry.FactoryRegistry
import com.github.karbonpowered.api.registry.provideFactory
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBufOutputStream
import io.netty.buffer.Unpooled
import io.netty.handler.codec.base64.Base64
import java.awt.image.BufferedImage
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO

class KarbonFavicon(
    override val image: BufferedImage
) : Favicon {
    override fun toString(): String = Factory.encode(image)

    object Factory : Favicon.Factory {
        private const val FAVICON_PREFIX = "data:image/png;base64,"

        override fun load(bufferedImage: BufferedImage): Favicon = KarbonFavicon(bufferedImage)

        override fun load(inputStream: InputStream): Favicon = load(ImageIO.read(inputStream))

        override fun load(url: URL): Favicon = load(ImageIO.read(url))

        override fun load(path: Path): Favicon = Files.newInputStream(path).use { load(it) }

        override fun load(raw: String): Favicon = load(decode(raw))

        internal fun encode(favicon: BufferedImage): String {
            require(favicon.width == 64) { "favicon must be 64 pixels wide" }
            require(favicon.height == 64) { "favicon must be 64 pixels high" }

            val buf = Unpooled.buffer()
            try {
                ImageIO.write(favicon, "PNG", ByteBufOutputStream(buf))
                val base64 = Base64.encode(buf)
                try {
                    return "$FAVICON_PREFIX${base64.toString(Charsets.UTF_8)}"
                } finally {
                    base64.release()
                }
            } finally {
                buf.release()
            }
        }

        internal fun decode(encoded: String): BufferedImage {
            require(encoded.startsWith(FAVICON_PREFIX)) { "Unknown favicon format" }
            val base64 = Unpooled.copiedBuffer(encoded.substring(FAVICON_PREFIX.length), Charsets.UTF_8)
            try {
                val buf = Base64.decode(base64)
                try {
                    val result = ImageIO.read(ByteBufInputStream(buf))
                    check(result.width == 64) { "favicon must be 64 pixels wide" }
                    check(result.height == 64) { "favicon must be 64 pixels high" }
                    return result
                } finally {
                    buf.release()
                }
            } finally {
                base64.release()
            }
        }
    }
}