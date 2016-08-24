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

public class MainActivity extends AppCompatActivity {

    private RekoteHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            httpClient = initClient();
        } catch (RekoteException e) {
            httpClient = showDialogForAddress();
        }

        initButtons();
    }

    private RekoteHttpClient showDialogForAddress() {
        //TODO force correct url input
        Toast.makeText(this, "Invalid Address", Toast.LENGTH_LONG).show();
        return null;
    }

    private RekoteHttpClient initClient() throws RekoteException {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String urlAddress = prefs.getString(SettingsConstants.SERVER_URL, "");
        return new RekoteHttpClient(urlAddress);
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
                if (httpClient.shutdown()) {
                    Toast.makeText(MainActivity.this, "Shutdown successfull", Toast.LENGTH_LONG).show();
                    return;
                }
                    Toast.makeText(MainActivity.this, "Failure at shutdown", Toast.LENGTH_LONG).show();
            }
        });

        assert btnShutdownIn != null;
        btnShutdownIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Shutdown in dialog
            }
        });

        assert btnHostInfo != null;
        btnHostInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HostInfo info = httpClient.getHostInfo();
                showInfo(info);
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

    private void showInfo(HostInfo info) {
        //TODO show infos somehow
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
}
