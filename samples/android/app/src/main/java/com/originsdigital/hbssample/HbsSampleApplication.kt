package com.originsdigital.hbssample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.origins.onrewind.OnRewind
import com.origins.onrewind.domain.CompetitionConfiguration
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbssample.videos.PlayerActivity
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.domain.analytics.AnalyticsEvent
import com.originsdigital.hbswidgets.domain.analytics.AnalyticsEventListener
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class HbsSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SettingsActivity.setDefaultTheme(this)

        val competitionId: String = "fwc"
        val season: String = "2014"


        HbsSdk.init(
            context = this,
            baseUrl = "https://dev-hbs-stats-provider.origins-digital.com/",
            accountKey = "uZknQc_1h",
            competitionId = competitionId,
            season = season,
        )
        HbsSdk.setAnalyticsListener(object : AnalyticsEventListener {
            @SuppressLint("LogNotTimber")
            override fun onNewEvent(screen: String, event: AnalyticsEvent) {

                Log.d(screen, "analytic event: ${event.eventId} - ${event.eventName}")
            }
        })

        HbsSdk.setOnVideoClickListener(object : HbsSdk.OnVideoClickListener {
            override fun playVideoUrl(context: Context, videoUrl: String) {
                val intent = PlayerActivity.getLaunchIntent(context, videoUrl)
                context.startActivity(intent)
            }

            override fun playEventId(context: Context, eventId: String) {
                val intent = PlayerActivity.getLaunchIntentForMatchId(context, eventId)
                context.startActivity(intent)
            }
        })



        OnRewind.initialize(
            OnRewind.InitParams.Builder()
                .setApplicationContext(this)
                .setBaseUrl("https://dev-hbs-stats-provider.origins-digital.com/")
                .setAccountKey("6GOG5kQMD")
                .setCompetitionConfiguration(
                    CompetitionConfiguration(competitionId, season)
                )
                .setAkamaiPrivateKey("0df73252ceaf17d78589371d5b8d1bbb")
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
