package com.originsdigital.hbssample.championship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentChampionshipBinding
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class SampleChampionshipFragment : BaseSampleFragment<FragmentChampionshipBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.championship.setOnMatchClickListener(object : OnMatchClickListener {
            override fun onMatchClicked(matchId: String) {
                Toast.makeText(requireContext(), "Match clicked: $matchId", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChampionshipBinding {
        return FragmentChampionshipBinding.inflate(inflater, container, false)
    }
}