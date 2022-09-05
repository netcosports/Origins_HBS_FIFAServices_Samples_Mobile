package com.originsdigital.hbssample.matchcenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentSampleMatchCenterBinding

class SampleMatchStatsFragment : BaseSampleFragment<FragmentSampleMatchCenterBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSampleMatchCenterBinding {
        return FragmentSampleMatchCenterBinding.inflate(inflater, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matchId = requireNotNull(arguments?.getString(MATCH_ID))

        listOf("Lineup", "Stats").map { text ->
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

        fun newInstance(matchId: String): SampleMatchStatsFragment {
            val fragment = SampleMatchStatsFragment()
            val args = Bundle()
            args.putString(MATCH_ID, matchId)
            fragment.arguments = args
            return fragment
        }
    }
}