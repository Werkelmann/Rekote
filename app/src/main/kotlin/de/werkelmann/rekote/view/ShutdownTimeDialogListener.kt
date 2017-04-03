package de.werkelmann.rekote.view

interface ShutdownTimeDialogListener {
    fun shutdownIn(minutes: Int)
    fun showErrorDialog()
}
