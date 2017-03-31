package de.werkelmann.rekote.address.ip

import de.werkelmann.rekote.address.ConnectionRule
import org.apache.commons.validator.routines.InetAddressValidator

class IsIpRule : ConnectionRule {
    override fun isValid(address: String): Boolean {
        return InetAddressValidator.getInstance().isValid(address)
    }
}
