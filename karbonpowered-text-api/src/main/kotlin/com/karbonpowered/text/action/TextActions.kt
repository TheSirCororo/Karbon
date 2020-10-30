package com.karbonpowered.text.action

import com.karbonpowered.text.Text
import java.net.URL
import java.nio.file.Path

object TextActions {
    @JvmStatic
    fun openUrl(url: URL): ClickAction.OpenUrl = ClickAction.OpenUrl.of(url)

    @JvmStatic
    fun openFile(path: Path): ClickAction.OpenFile = ClickAction.OpenFile.of(path)

    @JvmStatic
    fun runCommand(command: String): ClickAction.RunCommand = ClickAction.RunCommand.of(command)

    @JvmStatic
    fun suggestCommand(command: String): ClickAction.SuggestCommand = ClickAction.SuggestCommand.of(command)

    @JvmStatic
    fun changePage(page: Int): ClickAction.ChangePage = ClickAction.ChangePage.of(page)

    @JvmStatic
    fun copyToClipboard(text: String): ClickAction.CopyToClipboard = ClickAction.CopyToClipboard.of(text)

    @JvmStatic
    fun showText(text: Text): HoverAction.ShowText = HoverAction.ShowText.of(text)

    @JvmStatic
    fun insertText(text: String): ShiftClickAction.InsertText = ShiftClickAction.InsertText.of(text)
}