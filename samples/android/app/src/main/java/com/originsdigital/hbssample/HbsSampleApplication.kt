package com.originsdigital.hbssample

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.netcosports.onrewind.OnRewind
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbssample.videos.PlayerActivity
import com.originsdigital.hbswidgets.core.HbsSdk

class HbsSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        SettingsActivity.setDefaultTheme(this)
        HbsSdk.init(context = this)
        HbsSdk.setOnVideoClickListener(object : HbsSdk.OnVideoClickListener {
            override fun playVideoUrl(context: Context, videoUrl: String) {
                val intent = PlayerActivity.getLaunchIntent(context, videoUrl)
                context.startActivity(intent)
            }

            override fun playEventId(context: Context, eventId: String) {
                Toast.makeText(context, "Play eventId", Toast.LENGTH_SHORT).show()
            }
        })

        OnRewind.initialize(
            OnRewind.InitParams.Builder()
                .setApplicationContext(this)
                .setBaseUrl("https://api-gateway.onrewind.tv/main-api/")
                .build()
        )
    }
}