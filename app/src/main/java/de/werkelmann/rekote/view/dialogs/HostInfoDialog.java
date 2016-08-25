package de.werkelmann.rekote.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import de.werkelmann.rekote.R;
import de.werkelmann.rekote.model.HostInfo;

public class HostInfoDialog extends DialogFragment {

    private HostInfo info;

    public void setHostInfo(HostInfo info) {
        this.info = info;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_info, null);
        ((TextView) v.findViewById(R.id.tv_host_value)).setText(info.getHostName());
        ((TextView) v.findViewById(R.id.tv_ip_value)).setText(info.getIpAddress());

        builder.setView(v)
                .setTitle(R.string.info)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
