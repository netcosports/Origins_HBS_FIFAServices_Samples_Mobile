package com.originsdigital.hbssample

import android.os.Build
import android.os.Bundle
import android.util.LayoutDirection
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.coreui.doOnApplyWindowInsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestFullScreen()
        super.onCreate(savedInstanceState)
        setupStatusBar()
        setContentView(R.layout.activity_main)

        when (SettingsActivity.getLayoutDirection(this)) {
            SettingsActivity.AppLayoutDirection.AUTO -> {}
            SettingsActivity.AppLayoutDirection.LTR ->  {
                window.decorView.layoutDirection =  View.LAYOUT_DIRECTION_LTR
            }
            SettingsActivity.AppLayoutDirection.RTL -> {
                window.decorView.layoutDirection =  View.LAYOUT_DIRECTION_RTL
            }
        }

        findViewById<View>(R.id.main_view).doOnApplyWindowInsets { view, windowInsetsCompat, _ ->
            val insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = insets.top)
            return@doOnApplyWindowInsets windowInsetsCompat
        }
    }

    @Suppress("DEPRECATION")
    private fun setupStatusBar() {
        val isLight = AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES
        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            if (isLight) {
                decorView.windowInsetsController?.setSystemBarsAppearance(
                    APPEARANCE_LIGHT_STATUS_BARS,
                    APPEARANCE_LIGHT_STATUS_BARS
                )
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isLight) {
                // Draw dark icons on a light background color
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            } else {

                // Draw light icons on a dark background color
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()

            }
        }
    }

    @Suppress("DEPRECATION")
    private fun requestFullScreen() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onStart() {
        super.onStart()

        (application as HbsSampleApplication).setupMatchClickListener()

        val direction = SettingsActivity.getLayoutDirection(this)
        if (direction != SettingsActivity.AppLayoutDirection.AUTO) {

            if (direction == SettingsActivity.AppLayoutDirection.RTL && window.decorView.layoutDirection != LayoutDirection.RTL) {
                recreate()
            }

            if (direction == SettingsActivity.AppLayoutDirection.LTR && window.decorView.layoutDirection != LayoutDirection.LTR) {
                recreate()
            }
        }

    }

}
