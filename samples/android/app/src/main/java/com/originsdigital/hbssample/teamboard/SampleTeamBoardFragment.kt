package com.originsdigital.hbssample.teamboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbswidgets.android.databinding.FragmentTeamBoardBinding
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class SampleTeamBoardFragment : BaseSampleFragment<FragmentTeamBoardBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val commonTeamId = HbsSdk.getFavoriteTeamId() ?: "43946"
        binding.teamBoard.setupTeamId(commonTeamId, allowChangeTeam = true)
        if (needLocalMatchListener) {
            binding.teamBoard.hbsMatchClickListener = object : OnMatchClickListener {
                override fun onMatchClicked(context: Context, matchId: String) {
                    SampleMatchCenterActivity.launch(context, matchId = matchId, isLocal = true)
                }
            }
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamBoardBinding {
        return FragmentTeamBoardBinding.inflate(inflater, container, false)
    }

}