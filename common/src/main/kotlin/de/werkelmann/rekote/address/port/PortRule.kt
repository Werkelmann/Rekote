package de.werkelmann.rekote.address.port

import de.werkelmann.rekote.address.ConnectionRule

class PortRule : ConnectionRule {
    override fun isValid(address: String): Boolean {
        try {
            val portNumber = Integer.parseInt(address)
            return portNumber in 1..65535
        } catch (e: NumberFormatException) {
            return false
        }

    }
}
