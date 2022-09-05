package com.originsdigital.hbssample.h2h

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentHeadToHeadBinding

class HeadToHeadFragment : BaseSampleFragment<FragmentHeadToHeadBinding>() {

    private val type: HeadToHeadType
    get() = HeadToHeadType.values()[requireArguments().getInt(PARAM_TYPE)]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "Head To Head $type"
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        when (type) {
            HeadToHeadType.NO_TEAMS -> binding.headToHead.setupNoTeams()
            HeadToHeadType.ONE_TEAM -> binding.headToHead.setupOneTeamId(teamId = "43963")
            HeadToHeadType.TWO_TEAMS -> binding.headToHead.setupTwoTeamsIds(team1Id = "43938", team2Id = "43925")
        }

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHeadToHeadBinding {
        return FragmentHeadToHeadBinding.inflate(inflater, container, false)
    }

    enum class HeadToHeadType {
        NO_TEAMS, ONE_TEAM, TWO_TEAMS
    }

    companion object {
        private const val PARAM_TYPE = "param_type"
        fun buildArguments(type: HeadToHeadType): Bundle {
            return Bundle().apply {
                putInt(PARAM_TYPE, type.ordinal)
            }
        }
    }
}