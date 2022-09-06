package com.originsdigital.hbssample

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.origins.onrewind.OnRewind
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbssample.videos.PlayerActivity
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

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

    fun setupMatchClickListener() {
        if (SettingsActivity.getMatchClickHandler(this) == SettingsActivity.MatchClickHandler.GLOBAL) {
            HbsSdk.setOnMatchClickListener(listener = object : OnMatchClickListener {
                override fun onMatchClicked(context: Context, matchId: String) {
                    SampleMatchCenterActivity.launch(context, matchId = matchId, isLocal = false)
                }

            })
        } else {
            HbsSdk.setOnMatchClickListener(null)
        }
    }
}