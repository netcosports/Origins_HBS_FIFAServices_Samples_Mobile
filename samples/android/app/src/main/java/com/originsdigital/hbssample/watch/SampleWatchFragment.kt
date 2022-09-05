package com.originsdigital.hbssample.watch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentWatchBinding
import com.originsdigital.hbswidgets.core.HbsSdk

class SampleWatchFragment : BaseSampleFragment<FragmentWatchBinding>() {

    private val watchesType: WatchesType
        get() = WatchesType.values()[requireArguments().getInt(SampleWatchFragment.PARAM_TYPE)]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val groupId = "255937"
        val roundId = "255951"
        val teamId = "43960"
        val matchId = "84872"

        val widget = HbsSdk.watchesWidget(view.context)

        when (watchesType) {
            WatchesType.MATCH -> {
                widget.setMatchId(matchId = matchId)
            }
            WatchesType.ROUND -> {
                widget.setRoundId(roundId = roundId)
            }
            WatchesType.TEAM -> {
                widget.setTeamId(teamId = teamId)
            }
            WatchesType.GROUP -> {
                widget.setGroupId(groupId = groupId)
            }
        }

        binding.watchesContainer.addView(
            widget,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        private const val PARAM_TYPE = "param_type"

        fun buildArgs(type: WatchesType): Bundle {
            val bundle = Bundle().apply {
                putInt(PARAM_TYPE, type.ordinal)
            }
            return bundle
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWatchBinding {
        return FragmentWatchBinding.inflate(inflater, container, false)
    }
}