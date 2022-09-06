package com.originsdigital.hbssample.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentStatsTopPlayersBinding
import com.originsdigital.hbswidgets.stats.topplayer.TopPlayerStatsWidget

class SampleTopPlayerStatsFragment : BaseSampleFragment<FragmentStatsTopPlayersBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
//        binding.goals.setupStatsType(teamId = "43960", type = TopPlayerStatsWidget.Type.GOALS)
        binding.goals.setupStatsType(teamId = "43948", type = TopPlayerStatsWidget.Type.GOALS)
//        binding.shots.setupStatsType(teamId = "43946", type = TopPlayerStatsWidget.Type.SHOTS)
        binding.shots.setupStatsType(teamId = "43948", type = TopPlayerStatsWidget.Type.SHOTS)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatsTopPlayersBinding {
        return FragmentStatsTopPlayersBinding.inflate(inflater, container, false)
    }
}