package com.originsdigital.hbssample.groupstanding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentSampleStandingsRootBinding

class SampleStandingsRootFragment : Fragment() {

    private var _binding: FragmentSampleStandingsRootBinding? = null
    private val binding: FragmentSampleStandingsRootBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.compact.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupStandingRootFragment_to_groupStandingFragment,
                GroupStandingFragment.buildArgs(StandingsType.COMPACT)
            )
        }


        binding.expanded.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupStandingRootFragment_to_groupStandingFragment,
                GroupStandingFragment.buildArgs(StandingsType.EXPANDED)
            )
        }

        binding.singleGroupExpanded.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupStandingRootFragment_to_singleGroupStandingFragment,
                SingleGroupStandingFragment.buildArgs(
                    groupId = "275079",
                    type = StandingsType.EXPANDED
                )
            )
        }

        binding.singleGroupCompact.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupStandingRootFragment_to_singleGroupStandingFragment,
                SingleGroupStandingFragment.buildArgs(
                    groupId = "275079",
                    type = StandingsType.COMPACT
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSampleStandingsRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}