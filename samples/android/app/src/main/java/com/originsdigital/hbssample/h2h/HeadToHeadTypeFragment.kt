package com.originsdigital.hbssample.h2h

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentHeadToHeadTypeBinding

class HeadToHeadTypeFragment : BaseSampleFragment<FragmentHeadToHeadTypeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        binding.noTeams.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_fragment_to_headToHeadFragment,
                HeadToHeadFragment.buildArguments(HeadToHeadFragment.HeadToHeadType.NO_TEAMS)
            )
        }
        binding.oneTeam.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_fragment_to_headToHeadFragment,
                HeadToHeadFragment.buildArguments(HeadToHeadFragment.HeadToHeadType.ONE_TEAM)
            )
        }

        binding.twoTeams.setOnClickListener {
            findNavController().navigate(
                R.id.action_home_fragment_to_headToHeadFragment,
                HeadToHeadFragment.buildArguments(HeadToHeadFragment.HeadToHeadType.TWO_TEAMS)
            )
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHeadToHeadTypeBinding {
        return FragmentHeadToHeadTypeBinding.inflate(inflater, container, false)
    }
}