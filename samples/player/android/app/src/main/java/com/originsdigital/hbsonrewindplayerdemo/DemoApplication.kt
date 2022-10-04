package com.originsdigital.hbsonrewindplayerdemo

import android.app.Application
import com.origins.onrewind.OnRewind
import com.origins.onrewind.domain.CompetitionConfiguration

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        OnRewind.initialize(
            OnRewind.InitParams.Builder()
                .setApplicationContext(this)
                .setBaseUrl("https://dev-hbs-stats-provider.origins-digital.com/api/hbs/")
                .setAccountKey("6GOG5kQMD")
                .setCompetitionConfiguration(
                    CompetitionConfiguration("rsh2_fac", "2021")
                )
                .setSportBuffConfiguration(
                    OnRewind.InitParams.SportBuffConfiguration("OD")
                )
                .setAkamaiPrivateKey("0df73252ceaf17d78589371d5b8d1bbb")
                .build()
        )
    }
}