package com.originsdigital.hbssample.groupstanding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupStandingBinding
import com.originsdigital.hbswidgets.core.HbsSdk

class SampleAllGroupStandingFragment : BaseSampleFragment<FragmentGroupStandingBinding>() {

    private val standingType: SampleStandingsType
        get() = SampleStandingsType.values()[requireArguments().getInt(PARAM_TYPE)]

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGroupStandingBinding {
        return FragmentGroupStandingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val widget = HbsSdk.standingsWidget(view.context)
        widget.setupAllGroups(isExpanded = standingType == SampleStandingsType.EXPANDED)

        binding.widgetContainer.addView(
            widget,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        private const val PARAM_TYPE = "param_type"

        fun buildArgs(type: SampleStandingsType): Bundle {
            val bundle = Bundle().apply {
                putInt(PARAM_TYPE, type.ordinal)
            }
            return bundle
        }
    }
}
