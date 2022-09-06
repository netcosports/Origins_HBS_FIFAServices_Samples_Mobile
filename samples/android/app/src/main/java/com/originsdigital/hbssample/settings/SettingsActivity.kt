package com.originsdigital.hbssample.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.ActivitySettingsBinding
import com.originsdigital.hbswidgets.core.HbsSdk

class SettingsActivity : AppCompatActivity() {

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


        binding.direction.check(
            (when (getLayoutDirection(this)) {
                AppLayoutDirection.AUTO -> binding.directionAuto
                AppLayoutDirection.LTR -> binding.directionLtr
                AppLayoutDirection.RTL -> binding.directionRtl
            }).id
        )
        binding.direction.setOnCheckedChangeListener { _, checkedId ->
            val direction = when (checkedId) {
                binding.directionLtr.id -> AppLayoutDirection.LTR
                binding.directionRtl.id -> AppLayoutDirection.RTL
                else -> AppLayoutDirection.AUTO
            }
            setLayoutDirection(this@SettingsActivity, direction)
        }

        binding.matchCenter.check(
            (when (getMatchClickHandler(this)) {
                MatchClickHandler.INTERNAL -> binding.matchCenterInternal
                MatchClickHandler.LOCAL -> binding.matchCenterLocal
                MatchClickHandler.GLOBAL -> binding.matchCenterGlobal
            }).id
        )
        binding.matchCenter.setOnCheckedChangeListener { _, checkedId ->
            val matchCenterHandler = when (checkedId) {
                binding.matchCenterGlobal.id -> MatchClickHandler.GLOBAL
                binding.matchCenterLocal.id -> MatchClickHandler.LOCAL
                else -> MatchClickHandler.INTERNAL
            }
            setMatchClickHandler(this@SettingsActivity, matchCenterHandler)
        }

        val dm = resources.displayMetrics
        binding.textSize.text = "Font scale: ${dm.scaledDensity / dm.density}"

    }

    enum class AppLayoutDirection {
        AUTO, LTR, RTL
    }

    enum class MatchClickHandler {
        INTERNAL, LOCAL, GLOBAL
    }

    companion object {
        private const val PREFS_THEME = "prefs_theme"
        private const val PREFS_LAYOUT_DIRECTIONS = "prefs_layout_direction"
        private const val PREFS_MATCH_CLICK_HANDLER = "prefs_match_click_handler"

        private fun isLight(context: Context): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREFS_THEME, false)
        }

        fun getLayoutDirection(context: Context): AppLayoutDirection {
            return AppLayoutDirection.values()
                .getOrElse(
                    PreferenceManager.getDefaultSharedPreferences(context)
                        .getInt(PREFS_LAYOUT_DIRECTIONS, 0)
                ) {
                    AppLayoutDirection.AUTO
                }

        }

        fun setLayoutDirection(context: Context, direction: AppLayoutDirection) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREFS_LAYOUT_DIRECTIONS, direction.ordinal)
                .apply()
        }

        fun getMatchClickHandler(context: Context): MatchClickHandler {
            return MatchClickHandler.values()
                .getOrElse(
                    PreferenceManager.getDefaultSharedPreferences(context)
                        .getInt(PREFS_MATCH_CLICK_HANDLER, 0)
                ) {
                    MatchClickHandler.INTERNAL
                }

        }

        fun setMatchClickHandler(context: Context, handler: MatchClickHandler) {
            PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putInt(PREFS_MATCH_CLICK_HANDLER, handler.ordinal)
                .apply()
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