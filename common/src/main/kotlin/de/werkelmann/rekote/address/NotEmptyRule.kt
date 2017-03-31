package de.werkelmann.rekote.address

class NotEmptyRule : ConnectionRule {
    override fun isValid(address: String): Boolean {
        return !address.isEmpty()
    }
}
