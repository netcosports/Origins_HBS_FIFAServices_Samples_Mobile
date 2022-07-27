package com.originsdigital.hbssample.groupstanding

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupStandingBinding
import com.originsdigital.hbswidgets.core.HbsSdk

class GroupStandingFragment : Fragment() {

    private val standingType: StandingsType
        get() = StandingsType.values()[requireArguments().getInt(PARAM_TYPE)]

    private var _binding: FragmentGroupStandingBinding? = null
    private val binding: FragmentGroupStandingBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupStandingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val widget = HbsSdk.standingsWidget(view.context)
        widget.setupAllGroups()
        widget.setupDisplayParams(
            isExpanded = standingType == StandingsType.EXPANDED
        )
        binding.widgetContainer.addView(
            widget,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val PARAM_TYPE = "param_type"

        fun buildArgs(type: StandingsType): Bundle {
            val bundle = Bundle().apply {
                putInt(PARAM_TYPE, type.ordinal)
            }
            return bundle
        }
    }
}
