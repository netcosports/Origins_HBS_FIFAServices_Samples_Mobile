package com.originsdigital.hbssample.groupstanding

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.R
import com.originsdigital.hbssample.SampleApplication
import com.originsdigital.hbssample.databinding.FragmentGroupStandingPagerBinding

class StandingsRootFragment : Fragment() {

    private var _binding: FragmentGroupStandingPagerBinding? = null
    private val binding: FragmentGroupStandingPagerBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupStandingPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}