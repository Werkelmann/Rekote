package de.werkelmann.rekote.address.port

import de.werkelmann.rekote.address.ServerConnectionChecker

class PortChecker : ServerConnectionChecker() {
    init {
        rules.add(PortRule())
    }
}
