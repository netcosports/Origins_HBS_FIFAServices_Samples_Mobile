package com.originsdigital.hbssample.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupMatchesPagerBinding

class MatchesTypeFragment : BaseSampleFragment<FragmentGroupMatchesPagerBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.group.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                SampleMatchesFragment.buildArgs(MatchesType.GROUP)
            )
        }

        binding.round.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                SampleMatchesFragment.buildArgs(MatchesType.ROUND)
            )
        }


        binding.team.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                SampleMatchesFragment.buildArgs(MatchesType.TEAM)
            )
        }

        binding.singleMatch.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                SampleMatchesFragment.buildArgs(MatchesType.MATCH)
            )
        }

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGroupMatchesPagerBinding {
        return FragmentGroupMatchesPagerBinding.inflate(inflater, container, false)
    }

}