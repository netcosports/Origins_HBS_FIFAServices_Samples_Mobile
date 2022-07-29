package com.originsdigital.hbssample.groupstanding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentGroupStandingBinding
import com.originsdigital.hbswidgets.core.HbsSdk

class SampleSingleGroupStandingFragment : BaseSampleFragment<FragmentGroupStandingBinding>() {

    private val standingType: SampleStandingsType
        get() = SampleStandingsType.values()[requireArguments().getInt(PARAM_TYPE)]

    private val groupId: String
        get() = requireArguments().getString(PARAM_GROUP_ID).orEmpty()


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGroupStandingBinding {
        return FragmentGroupStandingBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "Single Standing $standingType"
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val widget = HbsSdk.standingsWidget(view.context)
        widget.setupSingleGroup(
            groupId = groupId,
            isExpanded = standingType == SampleStandingsType.EXPANDED
        )

        binding.widgetContainer.addView(
            widget,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    companion object {
        private const val PARAM_TYPE = "param_type"
        private const val PARAM_GROUP_ID = "param_group_id"

        fun buildArgs(groupId: String, type: SampleStandingsType): Bundle {
            val bundle = Bundle().apply {
                putInt(PARAM_TYPE, type.ordinal)
                putString(PARAM_GROUP_ID, groupId)
            }
            return bundle
        }
    }


}
