package com.originsdigital.hbssample.matches

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.R
import com.originsdigital.hbssample.SampleApplication
import com.originsdigital.hbssample.databinding.FragmentGroupMatchesPagerBinding

class GroupMatchesPagerFragment : Fragment() {

    private var _binding: FragmentGroupMatchesPagerBinding? = null
    private val binding: FragmentGroupMatchesPagerBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }

        binding.solidGroup.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.SOLID_GROUP_COMPACT)
            )
        }

        binding.transparentGroup.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.TRANSPARENT_GROUP_COMPACT)
            )
        }

        binding.solidRound.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.SOLID_ROUND_COMPACT)
            )
        }

        binding.transparentRound.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.TRANSPARENT_ROUND_COMPACT)
            )
        }

        binding.solidTeam.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.SOLID_TEAM_COMPACT)
            )
        }

        binding.transparentTeam.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.TRANSPARENT_TEAM_COMPACT)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupMatchesPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}