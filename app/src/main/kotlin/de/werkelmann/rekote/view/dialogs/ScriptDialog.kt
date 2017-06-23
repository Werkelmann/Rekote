package de.werkelmann.rekote.view.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.InputType
import android.widget.EditText
import de.werkelmann.rekote.R
import de.werkelmann.rekote.view.ScriptDialogListener

class ScriptDialog : DialogFragment() {

    private var mListener: ScriptDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)

        val editScript = EditText(context)
        editScript.inputType = InputType.TYPE_CLASS_TEXT

        builder.setMessage(R.string.script)
                .setView(editScript)
                .setPositiveButton(R.string.ok) { _, _ ->
                    val cmd = editScript.text.toString()
                    mListener!!.runScript(cmd)
                }

        return builder.create()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as ScriptDialogListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement ScriptDialogListener")
        }
    }

}
