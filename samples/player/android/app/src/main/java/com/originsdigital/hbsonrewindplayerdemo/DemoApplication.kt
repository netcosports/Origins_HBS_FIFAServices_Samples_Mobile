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
                .setAccountKey(TODO("ACCOUNT_KEY"))
                .setCompetitionConfiguration(
                    CompetitionConfiguration("rsh2_fac", "2021")
                )
                .setSportBuffConfiguration(
                    OnRewind.InitParams.SportBuffConfiguration(TODO("SPORT_BUFF_CLIENT_ACCOUNT"))
                )
                .setAkamaiPrivateKey(TODO("AKAMAI_PRIVATE_KEY"))
                .build()
        )
    }
}