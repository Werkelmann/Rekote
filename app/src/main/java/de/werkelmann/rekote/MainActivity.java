package de.werkelmann.rekote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.werkelmann.rekote.client.RekoteHttpClient;
import de.werkelmann.rekote.model.HostInfo;
import de.werkelmann.rekote.settings.SettingsActivity;
import de.werkelmann.rekote.settings.SettingsConstants;
import de.werkelmann.rekote.util.RekoteException;
import de.werkelmann.rekote.view.HostAddressInputDialogListener;
import de.werkelmann.rekote.view.ShutdownTimeDialogListener;
import de.werkelmann.rekote.view.dialogs.DialogFactory;

public class MainActivity extends AppCompatActivity implements HostAddressInputDialogListener, ShutdownTimeDialogListener {

    private RekoteHttpClient httpClient;
    private DialogFactory dialogFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialogFactory = new DialogFactory(this);

        try {
            httpClient = initClient();
        } catch (RekoteException e) {
            showDialogForAddress();
        }

        initButtons();
    }

    private void showDialogForAddress() {
        dialogFactory.showAddressInputDialog();
    }

    private RekoteHttpClient initClient() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String urlAddress = prefs.getString(SettingsConstants.SERVER_ADDRESS, "");
        String port = prefs.getString(SettingsConstants.SERVER_PORT, "");
        return new RekoteHttpClient(urlAddress, port);
    }

    private void initButtons() {
        Button btnShutdown = (Button) findViewById(R.id.btn_shutdown);
        Button btnShutdownIn = (Button) findViewById(R.id.btn_shutdown_in);
        Button btnHostInfo = (Button) findViewById(R.id.btn_host_info);
        Button btnStopShutdown = (Button) findViewById(R.id.btn_stop_shutdown);

        assert btnShutdown != null;
        btnShutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResponseInToast(httpClient.shutdown());
            }
        });

        assert btnShutdownIn != null;
        btnShutdownIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFactory.showShutdownTimeDialog();
            }
        });

        assert btnHostInfo != null;
        btnHostInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HostInfo info = httpClient.getHostInfo();
                    dialogFactory.showInfoDialog(info);
                } catch (RekoteException e) {
                    dialogFactory.showInfoExceptionDialog();
                }
            }
        });

        assert btnStopShutdown != null;
        btnStopShutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpClient.stopShutdown();
            }
        });
    }

    private void showResponseInToast(boolean isSuccessful) {
        if (isSuccessful) {
            Toast.makeText(MainActivity.this, "Shutdown successful", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(MainActivity.this, "Failure at shutdown", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateClientSettings(String address, String port) {
        try {
            httpClient = new RekoteHttpClient(address, port);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(SettingsConstants.SERVER_ADDRESS, address);
            editor.putString(SettingsConstants.SERVER_PORT, port);
            editor.apply();
        } catch (RekoteException e) {
            Toast.makeText(this, "Still invalid", Toast.LENGTH_LONG).show();
            showDialogForAddress();
        }
    }

    @Override
    public void shutdown(int minutes) {
        showResponseInToast(httpClient.shutdownIn(minutes));
    }
}
