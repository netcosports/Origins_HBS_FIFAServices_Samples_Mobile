package com.originsdigital.hbssample.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.originsdigital.hbswidgets.android.R

class SettingsActivity: AppCompatActivity(R.layout.activity_settings) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkAppTheme)
        } else {
            setTheme(R.style.AppTheme)
        }
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.check(if (isLight(this)) R.id.light else R.id.dark)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            setTheme(this@SettingsActivity, checkedId == R.id.light)
        }

    }

    companion object {
        private const val PREFS_THEME = "prefs_theme"

        private fun isLight(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREFS_THEME, false)
        }

        fun setDefaultTheme(context: Context) {
            setTheme(context, isLight = isLight(context))
        }

        private fun setTheme(context: Context, isLight: Boolean) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREFS_THEME, isLight)
                .apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isLight) {
                    AppCompatDelegate.MODE_NIGHT_NO
                } else {
                    AppCompatDelegate.MODE_NIGHT_YES
                }
            )
        }

        fun getLaunchIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }
}