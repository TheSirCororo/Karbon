package com.karbonpowered.translation

import java.net.URL
import java.security.AccessController
import java.security.PrivilegedActionException
import java.security.PrivilegedExceptionAction
import java.util.*

object UTF8ResourceBundleControl : ResourceBundle.Control() {
    override fun newBundle(
        baseName: String?,
        locale: Locale?,
        format: String,
        loader: ClassLoader,
        reload: Boolean
    ): ResourceBundle? {
        if (format == "java.properties") {
            val bundle = toBundleName(baseName, locale)
            val resource = if (bundle.endsWith(".properties")) bundle else toResourceName(bundle, "properties")
            val inputStream = try {
                AccessController.doPrivileged(PrivilegedExceptionAction {
                    val url = loader.getResource(resource) ?: URL(resource)
                    url.openConnection()?.let {
                        it.useCaches = false
                        it.getInputStream()
                    }
                })
            } catch (e: PrivilegedActionException) {
                throw e.exception
            }
            return inputStream?.bufferedReader()?.use {
                PropertyResourceBundle(it)
            }
        } else {
            return super.newBundle(baseName, locale, format, loader, reload)
        }
    }
}