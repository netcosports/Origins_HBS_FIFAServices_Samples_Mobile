package com.originsdigital.hbssample.watch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupWatchesPagerBinding

class SampleWatchesTypeFragment : BaseSampleFragment<FragmentGroupWatchesPagerBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.group.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupWatches_fragment_to_groupWatchesFragment,
                SampleWatchFragment.buildArgs(WatchesType.GROUP)
            )
        }

        binding.round.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupWatches_fragment_to_groupWatchesFragment,
                SampleWatchFragment.buildArgs(WatchesType.ROUND)
            )
        }


        binding.team.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupWatches_fragment_to_groupWatchesFragment,
                SampleWatchFragment.buildArgs(WatchesType.TEAM)
            )
        }

        binding.singleMatch.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupWatches_fragment_to_groupWatchesFragment,
                SampleWatchFragment.buildArgs(WatchesType.MATCH)
            )
        }

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGroupWatchesPagerBinding {
        return FragmentGroupWatchesPagerBinding.inflate(inflater, container, false)
    }
}