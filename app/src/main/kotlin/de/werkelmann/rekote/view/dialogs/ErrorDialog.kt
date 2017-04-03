package de.werkelmann.rekote.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import de.werkelmann.rekote.R

class ErrorDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return build()
    }

    fun build(): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(getString(R.string.trivial_error_message))
        return builder.create()
    }

}
