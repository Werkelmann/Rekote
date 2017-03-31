package de.werkelmann.rekote.address.url

import de.werkelmann.rekote.address.ConnectionRule

class UrlLabelsLengthRule : ConnectionRule {
    override fun isValid(address: String): Boolean {
        address.split(".".toRegex()).forEach {
            if (it.length > 63) {
                return false
            }
        }
        return true
    }
}
