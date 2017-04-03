package de.werkelmann.rekote.view.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment
import android.widget.EditText
import de.werkelmann.rekote.R
import de.werkelmann.rekote.settings.SettingsConstants
import de.werkelmann.rekote.view.HostAddressInputDialogListener

class HostAddressInputDialog : DialogFragment() {

    internal var mListener: HostAddressInputDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater

        val v = inflater.inflate(R.layout.dialog_address_input, null)
        val etAddress = v.findViewById(R.id.et_address) as EditText
        val etPort = v.findViewById(R.id.et_port) as EditText

        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        etAddress.setText(prefs.getString(SettingsConstants.SERVER_ADDRESS, ""))
        etPort.setText(prefs.getString(SettingsConstants.SERVER_PORT, ""))

        builder.setView(v)
                .setTitle(R.string.dialog_insert_valid_values)
                .setPositiveButton(R.string.ok) { dialog, _ ->
                    val address = etAddress.text.toString()
                    val port = etPort.text.toString()
                    mListener!!.updateClientSettings(address, port)
                    dialog.dismiss()
                }
        return builder.create()
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mListener = activity as HostAddressInputDialogListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement HostAddressInputDialogListener")
        }

    }
}
