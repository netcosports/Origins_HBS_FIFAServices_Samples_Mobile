package com.originsdigital.hbsonrewindplayerdemo

import android.app.Application
import com.netcosports.onrewind.OnRewind

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        OnRewind.initialize(
            OnRewind.InitParams.Builder()
                .setApplicationContext(this)
                .setBaseUrl("https://api-gateway.onrewind.tv/main-api/")
                .build()
        )
    }
}