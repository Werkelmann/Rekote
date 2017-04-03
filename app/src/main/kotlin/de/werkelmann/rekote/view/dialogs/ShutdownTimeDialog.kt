package de.werkelmann.rekote.view.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.InputType
import android.widget.EditText
import de.werkelmann.rekote.R
import de.werkelmann.rekote.view.ShutdownTimeDialogListener

class ShutdownTimeDialog : DialogFragment() {

    private var mListener: ShutdownTimeDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        val editTime = EditText(context)
        editTime.inputType = InputType.TYPE_CLASS_NUMBER

        builder.setMessage(R.string.set_shutdown_time)
                .setView(editTime)
                .setPositiveButton(R.string.ok) { _, _ ->
                    try {
                        val minutes = Integer.parseInt(editTime.text.toString())
                        mListener!!.shutdownIn(minutes)
                    } catch (e: NumberFormatException) {
                        mListener!!.showErrorDialog()
                    }
                }

        return builder.create()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as ShutdownTimeDialogListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement ShutdownTimeDialogListener")
        }

    }
}
