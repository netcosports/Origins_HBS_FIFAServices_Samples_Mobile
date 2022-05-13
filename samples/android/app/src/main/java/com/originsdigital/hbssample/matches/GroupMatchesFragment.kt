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
import com.originsdigital.hbswidgets.core.HbsSdk

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

        val widget = HbsSdk.matchesWidget(view.context)
        val widgetMedium = HbsSdk.matchesMediumWidget(view.context)
        val widgetLarge = HbsSdk.matchesLargeWidget(view.context)

        val commonId = "275075"

        when (matchesType) {
            MatchesType.SOLID_GROUP_COMPACT -> {
                widget.setupWithGroup(groupId = commonId)
                widgetMedium.setupWithGroup(groupId = commonId)
                widgetLarge.setupWithGroup(groupId = commonId)
            }
            MatchesType.TRANSPARENT_GROUP_COMPACT -> {
                widget.setupWithGroup(groupId = commonId)
                widgetMedium.setupWithGroup(groupId = commonId)
                widgetLarge.setupWithGroup(groupId = commonId)
            }
            MatchesType.SOLID_ROUND_COMPACT -> {
                widget.setupWithRound(roundId = commonId)
                widgetMedium.setupWithRound(roundId = commonId)
                widgetLarge.setupWithRound(roundId = commonId)
            }
            MatchesType.TRANSPARENT_ROUND_COMPACT -> {
                widget.setupWithRound(roundId = commonId)
                widgetMedium.setupWithRound(roundId = commonId)
                widgetLarge.setupWithRound(roundId = commonId)
            }
            MatchesType.SOLID_TEAM_COMPACT -> {
                widget.setupWithTeam(teamId = commonId)
                widgetMedium.setupWithTeam(teamId = commonId)
                widgetLarge.setupWithTeam(teamId = commonId)
            }
            MatchesType.TRANSPARENT_TEAM_COMPACT -> {
                widget.setupWithTeam(teamId = commonId)
                widgetMedium.setupWithTeam(teamId = commonId)
                widgetLarge.setupWithTeam(teamId = commonId)
            }
        }
        widgetLarge.setupDisplayParams(
            isTransparent = !matchesType.solid
        )

        widgetMedium.setupDisplayParams(
            isTransparent = !matchesType.solid
        )

        widget.setupDisplayParams(isTransparent = !matchesType.solid)

        binding.widgetContainer.addView(
            widget,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        binding.mediumWidgetContainer.addView(
            widgetMedium,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

//        binding.largeWidgetContainer.addView(
//            widgetLarge,
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
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