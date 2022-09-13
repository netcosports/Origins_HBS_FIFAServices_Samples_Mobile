package com.originsdigital.hbssample.matches

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupMatchesBinding
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.matchcenter.MatchWidget
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class SampleMatchesFragment : BaseSampleFragment<FragmentGroupMatchesBinding>() {

    private val matchesType: MatchesType
        get() = MatchesType.values()[requireArguments().getInt(PARAM_TYPE)]


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGroupMatchesBinding {
        return FragmentGroupMatchesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val widgetMedium = HbsSdk.matchesMediumWidget(view.context)

        val groupId = "255937"
        val roundId = "255951"
        val teamId = "43960"
        val matchId = "84872"
        val secondMatchId = "84864"

        when (matchesType) {
            MatchesType.GROUP -> {
                binding.smallMatches.setGroupId(groupId = groupId)
                widgetMedium.setGroupId(groupId = groupId)
                binding.largeMatches.setGroupId(groupId = groupId)
                binding.expandedMatches.setGroupId(groupId = groupId)
            }

            MatchesType.ROUND -> {
                binding.smallMatches.setRoundId(roundId = roundId)
                binding.largeMatches.setRoundId(roundId = roundId)
                binding.expandedMatches.setRoundId(roundId = roundId)
                widgetMedium.setRoundId(roundId = roundId)

            }

            MatchesType.TEAM -> {
                binding.smallMatches.setTeamId(teamId = teamId)
                binding.largeMatches.setTeamId(teamId = teamId)
                binding.expandedMatches.setTeamId(teamId = teamId)
                widgetMedium.setTeamId(teamId = teamId)
            }
            MatchesType.MATCH -> {
                binding.smallMatches.setMatchId(matchId = matchId)
                binding.largeMatches.setMatchId(matchId = matchId)
                binding.expandedMatches.setMatchId(matchId = matchId)
                widgetMedium.setMatchId(matchId = secondMatchId)
            }
        }


        binding.mediumWidgetContainer.addView(
            widgetMedium,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        if (needLocalMatchListener) {
            listOf<MatchWidget>(
                binding.smallMatches,
                widgetMedium,
                binding.largeMatches,
                binding.expandedMatches,
            ).forEach { widget ->
                widget.hbsMatchClickListener = object : OnMatchClickListener {
                    override fun onMatchClicked(context: Context, matchId: String) {
                        SampleMatchCenterActivity.launch(context, matchId = matchId, isLocal = true)
                    }
                }
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