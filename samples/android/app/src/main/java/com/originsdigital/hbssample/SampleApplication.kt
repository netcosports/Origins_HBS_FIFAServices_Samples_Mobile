package com.originsdigital.hbssample

import android.app.Application
import com.netcosports.onrewind.OnRewind
import com.originsdigital.hbswidgets.core.HbsSdk

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        HbsSdk.init(this)
        OnRewind.initialize(
            OnRewind.InitParams.Builder()
                .setApplicationContext(this)
                .setBaseUrl("https://api-gateway.onrewind.tv/main-api/")
                .build()
        )
        HbsSdk.allowMultipleFavoriteTeams(false)
    }


    companion object {
        //todo
        val backgroundResId = com.originsdigital.hbslibrary.R.drawable.hbs_dark_widget_background
    }
}