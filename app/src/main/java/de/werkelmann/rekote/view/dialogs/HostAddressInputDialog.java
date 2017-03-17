package de.werkelmann.rekote.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import de.werkelmann.rekote.R;
import de.werkelmann.rekote.settings.SettingsConstants;
import de.werkelmann.rekote.view.HostAddressInputDialogListener;

public class HostAddressInputDialog extends DialogFragment {

    HostAddressInputDialogListener mListener;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_address_input, null);
        final EditText etAddress = (EditText) v.findViewById(R.id.et_address);
        final EditText etPort = (EditText) v.findViewById(R.id.et_port);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        etAddress.setText(prefs.getString(SettingsConstants.SERVER_ADDRESS, ""));
        etPort.setText(prefs.getString(SettingsConstants.SERVER_PORT, ""));

        builder.setView(v)
                .setTitle(R.string.dialog_insert_valid_values)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String address = etAddress.getText().toString();
                        String port = etPort.getText().toString();
                        mListener.updateClientSettings(address, port);
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (HostAddressInputDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement HostAddressInputDialogListener");
        }
    }
}
