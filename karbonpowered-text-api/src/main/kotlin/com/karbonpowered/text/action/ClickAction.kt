package com.karbonpowered.text.action

import com.karbonpowered.commons.lang.loadService
import com.karbonpowered.text.format.TextStyle
import java.net.URL
import java.nio.file.Path

interface ClickAction<R> : TextAction<R> {
    override fun apply(builder: TextStyle.Builder) {
        builder.clickAction(this)
    }

    interface OpenUrl : ClickAction<URL> {
        override val name: String get() = "open_url"

        interface Factory {
            fun create(url: URL): OpenUrl
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(url: URL): OpenUrl = factory.create(url)
        }
    }

    interface OpenFile : ClickAction<Path> {
        override val name: String get() = "open_file"

        interface Factory {
            fun create(path: Path): OpenFile
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(path: Path): OpenFile = factory.create(path)
        }
    }

    interface RunCommand : ClickAction<String> {
        override val name: String get() = "run_command"

        interface Factory {
            fun create(command: String): RunCommand
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(command: String): RunCommand = factory.create(command)
        }
    }

    interface SuggestCommand : ClickAction<String> {
        override val name: String get() = "suggest_command"

        interface Factory {
            fun create(command: String): SuggestCommand
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(command: String): SuggestCommand = factory.create(command)
        }
    }

    interface ChangePage : ClickAction<Int> {
        override val name: String get() = "change_page"

        interface Factory {
            fun create(page: Int): ChangePage
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(page: Int): ChangePage = factory.create(page)
        }
    }

    interface CopyToClipboard : ClickAction<String> {
        override val name: String get() = "copy_to_clipboard"

        interface Factory {
            fun create(text: String): CopyToClipboard
        }

        companion object {
            private val factory = loadService<Factory>()

            @JvmStatic
            fun of(text: String): CopyToClipboard = factory.create(text)
        }
    }
}