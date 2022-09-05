package com.originsdigital.hbssample.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentStatsTeamMatchesBinding

class SampleTeamMatchesStatsFragment : BaseSampleFragment<FragmentStatsTeamMatchesBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.teamMatchesWidget.setupTeamId(teamId = "43960")
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatsTeamMatchesBinding {
        return FragmentStatsTeamMatchesBinding.inflate(inflater, container, false)
    }

}