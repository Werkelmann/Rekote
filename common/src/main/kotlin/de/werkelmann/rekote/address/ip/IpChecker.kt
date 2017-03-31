package de.werkelmann.rekote.address.ip

import de.werkelmann.rekote.address.NotEmptyRule
import de.werkelmann.rekote.address.ServerConnectionChecker

class IpChecker : ServerConnectionChecker() {
    init {
        rules.add(NotEmptyRule())
        rules.add(IsIpRule())
    }
}
