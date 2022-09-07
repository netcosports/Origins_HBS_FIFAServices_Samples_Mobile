package com.originsdigital.hbssample.matchcenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.originsdigital.hbswidgets.android.databinding.ActivityMatchCenterBinding

class SampleMatchCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMatchCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val matchId = intent.getStringExtra(MATCH_ID) ?: return
        binding.toolbar.title = if (intent.getBooleanExtra(
                IS_LOCAL,
                false
            )
        ) "Local match center" else "Global match center"
        binding.toolbar.setNavigationOnClickListener { finish() }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragment.id, SampleMatchCenterFragment.newInstance(matchId = matchId, displayToolbar = false))
            .commit()
    }

    companion object {

        private const val MATCH_ID = "match_id"
        private const val IS_LOCAL = "is_local"

        fun launch(context: Context, matchId: String, isLocal: Boolean) {
            context.startActivity(
                Intent(context, SampleMatchCenterActivity::class.java)
                    .putExtra(MATCH_ID, matchId)
                    .putExtra(IS_LOCAL, isLocal)
            )
        }
    }
}