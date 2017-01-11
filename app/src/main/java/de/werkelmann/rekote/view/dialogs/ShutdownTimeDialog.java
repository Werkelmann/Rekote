package de.werkelmann.rekote.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.widget.EditText;
import de.werkelmann.rekote.R;
import de.werkelmann.rekote.view.ShutdownTimeDialogListener;

public class ShutdownTimeDialog extends DialogFragment {

    private ShutdownTimeDialogListener mListener;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final EditText editTime = new EditText(getContext());
        editTime.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setMessage(R.string.set_shutdown_time)
                .setView(editTime)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            int minutes = Integer.parseInt(editTime.getText().toString());
                            mListener.shutdownIn(minutes);
                        } catch (NumberFormatException e) {
                            mListener.showErrorDialog();
                        }
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ShutdownTimeDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ShutdownTimeDialogListener");
        }
    }
}
