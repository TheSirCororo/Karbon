package com.karbonpowered.api.command.registrar.tree

import com.karbonpowered.catalog.CatalogRegistry
import com.karbonpowered.catalog.getProvider

object ClientCompletionKeys {
    @JvmField
    val FLOAT = CatalogRegistry.getProvider<ClientCompletionKey<*>, ClientCompletionKey<CommandTreeNode.Literal>>("float").get()

    @JvmField
    val DOUBLE = CatalogRegistry.getProvider<ClientCompletionKey<*>, ClientCompletionKey<CommandTreeNode.Literal>>("double").get()

    @JvmField
    val INTEGER = CatalogRegistry.getProvider<ClientCompletionKey<*>, ClientCompletionKey<CommandTreeNode.Literal>>("integer").get()

    @JvmField
    val STRING = CatalogRegistry.getProvider<ClientCompletionKey<*>, ClientCompletionKey<CommandTreeNode.StringParser>>("string").get()
}