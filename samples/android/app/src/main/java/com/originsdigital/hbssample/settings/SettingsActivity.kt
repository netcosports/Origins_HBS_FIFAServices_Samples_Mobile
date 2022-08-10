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
import com.originsdigital.hbswidgets.android.databinding.ActivitySettingsBinding
import com.originsdigital.hbswidgets.core.HbsSdk

class SettingsActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkAppTheme)
        } else {
            setTheme(R.style.AppTheme)
        }
        binding.appTheme.check((if (isLight(this)) binding.light else binding.dark).id)
        binding.appTheme.setOnCheckedChangeListener { _, checkedId ->
            setTheme(this@SettingsActivity, checkedId == binding.light.id)
        }

        binding.actions.check((if (HbsSdk.isDisplayActionsInMatchCenter()) binding.display else binding.hide).id)
        binding.actions.setOnCheckedChangeListener { _, checkedId ->
            HbsSdk.displayActionsInMatchCenter(checkedId == binding.display.id)
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