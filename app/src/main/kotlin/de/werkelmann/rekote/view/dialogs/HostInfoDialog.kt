package de.werkelmann.rekote.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TextView
import de.werkelmann.rekote.R
import de.werkelmann.rekote.model.HostInfo

class HostInfoDialog(val info: HostInfo) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater

        val v = inflater.inflate(R.layout.dialog_info, null)
        (v.findViewById(R.id.tv_host_value) as TextView).text = info.hostName
        (v.findViewById(R.id.tv_ip_value) as TextView).text = info.ipAddress

        builder.setView(v)
                .setTitle(R.string.info)
                .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }
}
