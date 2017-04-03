package de.werkelmann.rekote.settings


import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.support.v4.app.NavUtils
import android.view.MenuItem
import de.werkelmann.rekote.R
import de.werkelmann.rekote.address.AddressChecker
import de.werkelmann.rekote.address.port.PortChecker

/**
 * A [PreferenceActivity] that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 *
 *
 * See [
   * Android Design: Settings](http://developer.android.com/design/patterns/settings.html) for design guidelines and the [Settings
   * API Guide](http://developer.android.com/guide/topics/ui/settings.html) for more information on developing a Settings UI.
 */
class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setupActionBar()

        fragmentManager.beginTransaction()
                .replace(R.id.settings_content, SettingsFragment())
                .commit()
    }

    /**
     * Set up the [android.app.ActionBar], if the API is available.
     */
    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this)
            }
            return true
        }
        return super.onMenuItemSelected(featureId, item)
    }

    class SettingsFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref)

            val urlPref = preferenceScreen
                    .findPreference(SettingsConstants.SERVER_ADDRESS) as EditTextPreference
            val portPref = preferenceScreen
                    .findPreference(SettingsConstants.SERVER_PORT) as EditTextPreference

            urlPref.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                AddressChecker().check(newValue.toString())
            }

            portPref.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                PortChecker().check(newValue.toString())
            }
        }
    }

}
