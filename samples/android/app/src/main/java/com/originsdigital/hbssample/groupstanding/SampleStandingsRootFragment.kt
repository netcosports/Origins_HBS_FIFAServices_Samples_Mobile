package com.originsdigital.hbssample.groupstanding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentSampleStandingsRootBinding

class SampleStandingsRootFragment : BaseSampleFragment<FragmentSampleStandingsRootBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }


        binding.singleGroupExpanded.setOnClickListener {
            findNavController().navigate(
                R.id.action_standingRootFragment_to_standingFragment,
                SampleStandingFragment.buildArgs(
                    groupId = "255933",
                    type = SampleStandingsType.EXPANDED
                )
            )
        }

        binding.singleGroupCompact.setOnClickListener {
            findNavController().navigate(
                R.id.action_standingRootFragment_to_standingFragment,
                SampleStandingFragment.buildArgs(
                    groupId = "255945",
                    type = SampleStandingsType.COMPACT
                )
            )
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSampleStandingsRootBinding {
        return FragmentSampleStandingsRootBinding.inflate(inflater, container, false)
    }

}