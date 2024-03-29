package com.originsdigital.hbssample.championship

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbswidgets.android.databinding.FragmentChampionshipBinding
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class SampleChampionshipFragment : BaseSampleFragment<FragmentChampionshipBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        if (needLocalMatchListener) {
            binding.championship.hbsMatchClickListener = object : OnMatchClickListener {
                override fun onMatchClicked(context: Context, matchId: String) {
                    SampleMatchCenterActivity.launch(context, matchId = matchId, isLocal = true)
                }
            }
        }


    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChampionshipBinding {
        return FragmentChampionshipBinding.inflate(inflater, container, false)
    }
}