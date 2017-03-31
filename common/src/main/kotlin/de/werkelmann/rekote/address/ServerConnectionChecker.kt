package de.werkelmann.rekote.address

import java.util.*

abstract class ServerConnectionChecker {

    protected val rules: MutableList<ConnectionRule> = LinkedList()

    open fun check(address: String): Boolean {
        rules.forEach { if (!it.isValid(address)) return false }
        return true
    }
}
