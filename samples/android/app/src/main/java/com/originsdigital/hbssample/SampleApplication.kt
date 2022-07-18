package com.originsdigital.hbssample

import android.app.Application
import android.content.Context
import com.netcosports.onrewind.OnRewind
import com.originsdigital.hbssample.player.PlayerActivity
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
        HbsSdk.setOnVideoClickListener(object : HbsSdk.OnVideoClickListener {
            override fun playEventId(context: Context, eventId: String) {
                TODO("Not yet implemented")
            }

            override fun playVideoUrl(context: Context, videoUrl: String) {
                val intent = PlayerActivity.getLaunchIntent(context, videoUrl)
                context.startActivity(intent)
            }

        })
    }


    companion object {
        //todo
        val backgroundResId = com.originsdigital.hbslibrary.R.drawable.hbs_dark_widget_background
    }
}