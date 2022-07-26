package com.originsdigital.hbssample.matches

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.SampleApplication
import com.originsdigital.hbssample.databinding.FragmentGroupMatchesBinding

class GroupMatchesFragment : Fragment() {

    private var _binding: FragmentGroupMatchesBinding? = null
    private val binding: FragmentGroupMatchesBinding
        get() = requireNotNull(_binding)

    private val matchesType: MatchesType
        get() = MatchesType.values()[requireArguments().getInt(PARAM_TYPE)]

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }

        val widget = binding.smallMatches
        val widgetMedium = binding.mediumMatches

        val groupId = "255937"
        val roundId = "255951"
        val teamId = "43960"
        val matchId = "84872"
        val secondMatchId = "84864"

        when (matchesType) {
            MatchesType.GROUP -> {
                widget.setGroupId(groupId = groupId)
                widgetMedium.setGroupId(groupId = groupId)
            }


            MatchesType.ROUND -> {
                widget.setRoundId(roundId = roundId)
                widgetMedium.setRoundId(roundId = roundId)
            }
            MatchesType.TEAM -> {
                widget.setTeamId(teamId = teamId)
                widgetMedium.setTeamId(teamId = teamId)
            }
            MatchesType.MATCH -> {
                widget.setMatchId(matchId = matchId)
                widgetMedium.setMatchId(matchId = secondMatchId)
            }

        }



    }

    companion object {
        private const val PARAM_TYPE = "param_type"

        fun buildArgs(type: MatchesType): Bundle {
            val bundle = Bundle().apply {
                putInt(PARAM_TYPE, type.ordinal)
            }
            return bundle
        }
    }
}