package io.github.dovecoteescapee.byedpi.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
import io.github.dovecoteescapee.byedpi.BuildConfig
import io.github.dovecoteescapee.byedpi.R
import io.github.dovecoteescapee.byedpi.data.Mode
import io.github.dovecoteescapee.byedpi.utility.*

class MainSettingsFragment : PreferenceFragmentCompat() {
    companion object {
        private val TAG: String = MainSettingsFragment::class.java.simpleName

        fun setTheme(name: String) =
            themeByName(name)?.let {
                AppCompatDelegate.setDefaultNightMode(it)
            } ?: throw IllegalStateException("Invalid value for app_theme: $name")

        private fun themeByName(name: String): Int? = when (name) {
            "system" -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            "light" -> AppCompatDelegate.MODE_NIGHT_NO
            "dark" -> AppCompatDelegate.MODE_NIGHT_YES
            else -> {
                Log.w(TAG, "Invalid value for app_theme: $name")
                null
            }
        }
    }

    private val preferenceListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            updatePreferences()
        }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_settings, rootKey)

        setEditTextPreferenceListener("dns_ip") {
            it.isBlank() || checkNotLocalIp(it)
        }

        findPreferenceNotNull<DropDownPreference>("app_theme")
            .setOnPreferenceChangeListener { _, newValue ->
                setTheme(newValue as String)
                true
            }

        val switchCommandLineSettings = findPreferenceNotNull<SwitchPreference>(
            "byedpi_enable_cmd_settings"
        )
        val uiSettings = findPreferenceNotNull<Preference>("byedpi_ui_settings")
        val cmdSettings = findPreferenceNotNull<Preference>("byedpi_cmd_settings")

        val setByeDpiSettingsMode = { enable: Boolean ->
            uiSettings.isEnabled = !enable
            cmdSettings.isEnabled = enable
        }

        setByeDpiSettingsMode(switchCommandLineSettings.isChecked)

        switchCommandLineSettings.setOnPreferenceChangeListener { _, newValue ->
            setByeDpiSettingsMode(newValue as Boolean)
            true
        }

        // SplitWire-Turkey: applying a strategy preset writes the ByeDPI command line
        // and switches to command-line mode. "custom" hands control back to the editors.
        findPreferenceNotNull<DropDownPreference>("strategy_preset")
            .setOnPreferenceChangeListener { _, newValue ->
                val preset = newValue as String
                if (preset == "custom") {
                    switchCommandLineSettings.isChecked = false
                    setByeDpiSettingsMode(false)
                } else {
                    sharedPreferences?.edit()
                        ?.putString("byedpi_cmd_args", preset)
                        ?.apply()
                    switchCommandLineSettings.isChecked = true
                    setByeDpiSettingsMode(true)
                }
                Toast.makeText(requireContext(), R.string.strategy_applied, Toast.LENGTH_SHORT)
                    .show()
                true
            }

        setupAppRouting()

        findPreferenceNotNull<Preference>("version").summary = BuildConfig.VERSION_NAME

        updatePreferences()
    }

    private fun setupAppRouting() {
        val routeSwitch = findPreferenceNotNull<SwitchPreference>("route_selected_apps_enable")
        val allowedApps = findPreferenceNotNull<MultiSelectListPreference>("allowed_apps")

        // List user-launchable apps (label + package), sorted by name.
        val pm = requireContext().packageManager
        val launcherIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val apps = pm.queryIntentActivities(launcherIntent, 0)
            .map { it.activityInfo.packageName to it.loadLabel(pm).toString() }
            .distinctBy { it.first }
            .sortedBy { it.second.lowercase() }

        allowedApps.entries = apps.map { it.second }.toTypedArray()
        allowedApps.entryValues = apps.map { it.first }.toTypedArray()

        fun updateAllowedSummary(count: Int) {
            allowedApps.summary =
                if (count == 0) getString(R.string.allowed_apps_summary_none)
                else getString(R.string.allowed_apps_summary_count, count)
        }

        updateAllowedSummary(allowedApps.values.size)
        allowedApps.isEnabled = routeSwitch.isChecked

        routeSwitch.setOnPreferenceChangeListener { _, newValue ->
            allowedApps.isEnabled = newValue as Boolean
            true
        }

        allowedApps.setOnPreferenceChangeListener { _, newValue ->
            @Suppress("UNCHECKED_CAST")
            updateAllowedSummary((newValue as? Set<String>)?.size ?: 0)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        sharedPreferences?.registerOnSharedPreferenceChangeListener(preferenceListener)
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences?.unregisterOnSharedPreferenceChangeListener(preferenceListener)
    }

    private fun updatePreferences() {
        val mode = findPreferenceNotNull<ListPreference>("byedpi_mode")
            .value.let { Mode.fromString(it) }
        val dns = findPreferenceNotNull<EditTextPreference>("dns_ip")
        val ipv6 = findPreferenceNotNull<SwitchPreference>("ipv6_enable")

        when (mode) {
            Mode.VPN -> {
                dns.isVisible = true
                ipv6.isVisible = true
            }

            Mode.Proxy -> {
                dns.isVisible = false
                ipv6.isVisible = false
            }
        }
    }
}
