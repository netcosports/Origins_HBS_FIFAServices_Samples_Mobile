package com.originsdigital.hbssample.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentStatsBinding

class StatsFragment: BaseSampleFragment<FragmentStatsBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.topPlayers.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_topPlayers)
        }

        binding.teamMatches.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_teamMatches)
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatsBinding {
        return FragmentStatsBinding.inflate(inflater, container, false)
    }

}