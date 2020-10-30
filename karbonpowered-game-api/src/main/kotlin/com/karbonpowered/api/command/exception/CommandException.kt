package com.karbonpowered.api.command.exception

import com.karbonpowered.text.Text
import com.karbonpowered.text.exception.TextMessageException

class CommandException(
        text: Text? = null,
        cause: Throwable? = null,
        val includeUsage: Boolean = false
) : TextMessageException(text, cause) {
    constructor(text: Text?, includeUsage: Boolean) : this(text, null, includeUsage)
}