package de.werkelmann.rekote.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import de.werkelmann.rekote.R

class InfoExceptionDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        builder.setMessage(R.string.error_no_info)
                .setTitle(R.string.info)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }

        return builder.create()
    }
}
