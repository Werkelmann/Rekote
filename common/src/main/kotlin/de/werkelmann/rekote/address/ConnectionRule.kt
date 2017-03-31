package de.werkelmann.rekote.address

interface ConnectionRule {
    fun isValid(address: String): Boolean
}
