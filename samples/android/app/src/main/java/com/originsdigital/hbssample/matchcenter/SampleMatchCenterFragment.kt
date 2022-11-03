package com.originsdigital.hbssample.matchcenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentSampleMatchCenterBinding

class SampleMatchCenterFragment : BaseSampleFragment<FragmentSampleMatchCenterBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSampleMatchCenterBinding {
        return FragmentSampleMatchCenterBinding.inflate(inflater, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.innerToolbar.isVisible = requireArguments().getBoolean(DISPLAY_TOOLBAR)
        binding.innerToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        val matchId = requireNotNull(arguments?.getString(MATCH_ID))

        binding.matchHeader.setupMatchId(matchId)
        binding.expandedMatchHeader.setupMatchId(matchId)

        listOf("Lineup", "Stats", "Header", "Expanded header").map { text ->
            val tab = binding.tabLayout.newTab()
            tab.text = text
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewAnimator.displayedChild = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        binding.matchStats.setupMatchId(matchId)
        binding.lineup.setupMatchId(matchId)
    }

    companion object {

        private const val MATCH_ID = "match_id"
        private const val DISPLAY_TOOLBAR = "display_toolbar"

        fun buildArgs(matchId: String, displayToolbar: Boolean): Bundle {
            val args = Bundle()
            args.putString(MATCH_ID, matchId)
            args.putBoolean(DISPLAY_TOOLBAR, displayToolbar)
            return args
        }

        fun newInstance(matchId: String, displayToolbar: Boolean): SampleMatchCenterFragment {
            val fragment = SampleMatchCenterFragment()
            fragment.arguments = buildArgs(matchId, displayToolbar)
            return fragment
        }
    }
}