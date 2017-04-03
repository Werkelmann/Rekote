package de.werkelmann.rekote.view.dialogs

import android.app.Activity
import android.support.v4.app.FragmentActivity
import de.werkelmann.rekote.model.HostInfo

class DialogFactory(private val context: Activity) {

    fun showInfoDialog(info: HostInfo) {
        show(HostInfoDialog(info), "info")
    }

    fun showAddressInputDialog() {
        show(HostAddressInputDialog(), "input")
    }

    fun showInfoExceptionDialog() {
        show(InfoExceptionDialog(), "input")
    }

    fun showShutdownTimeDialog() {
        show(ShutdownTimeDialog(), "input")
    }

    fun showErrorDialog() {
        show(ErrorDialog(), "input")
    }

    private fun show(dialog: android.support.v4.app.DialogFragment, tag: String) {
        dialog.show((context as FragmentActivity).supportFragmentManager, tag)
    }
}