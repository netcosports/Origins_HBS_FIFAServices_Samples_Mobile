package com.originsdigital.hbssample

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.origins.onrewind.OnRewind
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbssample.videos.PlayerActivity
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.domain.analytics.AnalyticsEvent
import com.originsdigital.hbswidgets.domain.analytics.AnalyticsEventListener
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class HbsSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        SettingsActivity.setDefaultTheme(this)

        HbsSdk.init(
            context = this,
            baseUrl = "https://dev-hbs-stats-provider.origins-digital.com/",
            accountKey = "YOUR_KEY",
            competitionId = "fwc",
            season = "2014",
        )

        HbsSdk.setAnalyticsListener(object : AnalyticsEventListener {

            override fun onNewEvent(screen: String, event: AnalyticsEvent) {
            }

        })

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