package com.originsdigital.hbssample.stats

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentStatsTopPlayersBinding
import com.originsdigital.hbswidgets.stats.topplayer.TopPlayerStatsWidget

class TopPlayerStatsFragment : Fragment() {

    private var _binding: FragmentStatsTopPlayersBinding? = null
    private val binding: FragmentStatsTopPlayersBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsTopPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.goals.setupStatsType(TopPlayerStatsWidget.Type.GOALS)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}