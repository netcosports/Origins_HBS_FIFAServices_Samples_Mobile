package com.originsdigital.hbssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.core.HbsSdk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
