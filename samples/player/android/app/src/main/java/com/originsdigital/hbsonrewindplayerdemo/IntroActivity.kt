package com.originsdigital.hbsonrewindplayerdemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class IntroActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        findViewById<View>(R.id.exo).setOnClickListener {
            startActivity(PlayerActivity.getLaunchIntent(this, false))
        }

        findViewById<View>(R.id.wrapper).setOnClickListener {
            startActivity(PlayerActivity.getLaunchIntent(this, true))
        }
    }
}