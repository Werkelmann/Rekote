package de.werkelmann.rekote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import de.werkelmann.rekote.call.CallStateListener
import de.werkelmann.rekote.client.RekoteClient
import de.werkelmann.rekote.client.RekoteHttpClient
import de.werkelmann.rekote.settings.SettingsActivity
import de.werkelmann.rekote.settings.SettingsConstants
import de.werkelmann.rekote.util.RekoteException
import de.werkelmann.rekote.view.HostAddressInputDialogListener
import de.werkelmann.rekote.view.ShutdownTimeDialogListener
import de.werkelmann.rekote.view.dialogs.DialogFactory
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), HostAddressInputDialogListener, ShutdownTimeDialogListener {

    private var httpClient: RekoteClient? = null
    private val dialogFactory: DialogFactory = DialogFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        initClient()
        initButtons()
        initCallListener()
    }

    private fun initClient() {
        try {
            httpClient = instantiateClient()
        } catch (e: RekoteException) {
            showDialogForInvalidAddress()
        }
    }

    private fun instantiateClient(): RekoteHttpClient {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val urlAddress = prefs.getString(SettingsConstants.SERVER_ADDRESS, "")
        val port = prefs.getString(SettingsConstants.SERVER_PORT, "")
        return RekoteHttpClient(urlAddress, port)
    }

    private fun showDialogForInvalidAddress() {
        dialogFactory.showAddressInputDialog()
    }

    private fun initButtons() {
        btn_shutdown.setOnClickListener { shutdownIn(0) }
        btn_shutdown_in.setOnClickListener { dialogFactory.showShutdownTimeDialog() }
        btn_host_info.setOnClickListener { showInfo() }
        btn_stop_shutdown.setOnClickListener { httpClient!!.stopShutdown() }
    }

    private fun initCallListener() {
        val phoneStateListener = CallStateListener(httpClient!!)
        val tm = applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        tm.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    private fun showResponseInToast(isSuccessful: Boolean) {
        if (isSuccessful) {
            Toast.makeText(this@MainActivity, "Shutdown successful", Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this@MainActivity, "Failure at shutdownIn", Toast.LENGTH_LONG).show()
    }

    private fun showInfo() {
        try {
            val info = httpClient!!.getHostInfo()
            dialogFactory.showInfoDialog(info)
        } catch (e: RekoteException) {
            dialogFactory.showInfoExceptionDialog()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun updateClientSettings(address: String, port: String) {
        try {
            httpClient = RekoteHttpClient(address, port)
            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = prefs.edit()
            editor.putString(SettingsConstants.SERVER_ADDRESS, address)
            editor.putString(SettingsConstants.SERVER_PORT, port)
            editor.apply()
        } catch (e: RekoteException) {
            Toast.makeText(this, "Still invalid", Toast.LENGTH_LONG).show()
            showDialogForInvalidAddress()
        }
    }

    override fun shutdownIn(minutes: Int) {
        showResponseInToast(httpClient!!.shutdownIn(minutes))
    }

    override fun showErrorDialog() {
        dialogFactory.showErrorDialog()
    }
}
