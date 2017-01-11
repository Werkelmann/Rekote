package de.werkelmann.rekote.view.dialogs;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
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
        DialogFragment dialog = new InfoExceptionDialog();
        dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "input");
    }

    public void showShutdownTimeDialog() {
        DialogFragment dialog = new ShutdownTimeDialog();
        dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "input");
    }

    public void showErrorDialog() {
        DialogFragment dialog = new ErrorDialog();
        dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "input");
    }
}