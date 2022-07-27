package com.originsdigital.hbssample.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentStatsBinding

class StatsFragment: Fragment() {

    private var _binging: FragmentStatsBinding? = null
    private val binding: FragmentStatsBinding
    get() = requireNotNull(_binging)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.topPlayers.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_topPlayers)
        }

        binding.teamMatches.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_teamMatches)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binging = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binging = null
        super.onDestroyView()
    }
}