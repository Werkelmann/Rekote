package de.werkelmann.rekote.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import de.werkelmann.rekote.R;
import de.werkelmann.rekote.model.HostInfo;

public class DialogFactory {

    private final Activity context;

    public DialogFactory(Activity context) {
        this.context = context;
    }

    public void showInfoDialog(HostInfo info) {
        HostInfoDialog dialog = new HostInfoDialog();
        dialog.setHostInfo(info);
        dialog.show(context.getFragmentManager(), "info");
    }

    public void showAddressInputDialog() {
        DialogFragment dialog = new HostAddressInputDialog();
        dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "input");
    }

    public void showInfoExceptionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(R.string.error_no_info)
                .setTitle(R.string.info)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
