package de.werkelmann.rekote.address.url

import de.werkelmann.rekote.address.ConnectionRule

class UrlValidBeginningAndEndRule : ConnectionRule {
    override fun isValid(address: String): Boolean {
        return !Character.isDigit(address[0]) && address[0] != '-' && address[address.length - 1] != '-'
    }
}
