package com.originsdigital.hbssample.h2h

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.databinding.FragmentHeadToHeadBinding

class HeadToHeadFragment : Fragment() {

    private var _binding: FragmentHeadToHeadBinding? = null
    private val binding: FragmentHeadToHeadBinding
        get() = requireNotNull(_binding)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadToHeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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