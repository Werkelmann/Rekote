package de.werkelmann.rekote.address.url

import de.werkelmann.rekote.address.ConnectionRule

class UrlValidCharRule : ConnectionRule {
    override fun isValid(address: String): Boolean {
        address.toCharArray().forEach {
            if (!Character.isLetter(it) && !Character.isDigit(it) && it != '-' && it != '.') {
                return false
            }
        }
        return true
    }
}
