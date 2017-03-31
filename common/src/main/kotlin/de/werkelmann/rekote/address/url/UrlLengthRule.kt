package de.werkelmann.rekote.address.url

import de.werkelmann.rekote.address.ConnectionRule

class UrlLengthRule : ConnectionRule {

    override fun isValid(address: String): Boolean {
        return address.length < 254
    }
}
