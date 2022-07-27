package com.originsdigital.hbssample.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupMatchesPagerBinding

class MatchesTypeFragment : Fragment() {

    private var _binding: FragmentGroupMatchesPagerBinding? = null
    private val binding: FragmentGroupMatchesPagerBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.group.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                MatchesFragment.buildArgs(MatchesType.GROUP)
            )
        }

        binding.round.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                MatchesFragment.buildArgs(MatchesType.ROUND)
            )
        }


        binding.team.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                MatchesFragment.buildArgs(MatchesType.TEAM)
            )
        }

        binding.singleMatch.setOnClickListener {
            findNavController().navigate(
                R.id.action_groupMatches_fragment_to_groupMatchesFragment,
                MatchesFragment.buildArgs(MatchesType.MATCH)
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