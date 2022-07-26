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

        binding.group.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.GROUP)
            )
        }


        binding.round.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.ROUND)
            )
        }

        binding.team.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.TEAM)
            )
        }

        binding.singleMatch.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                GroupMatchesFragment.buildArgs(MatchesType.MATCH)
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