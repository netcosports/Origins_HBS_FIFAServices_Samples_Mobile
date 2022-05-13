package com.originsdigital.hbssample.stats

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.R
import com.originsdigital.hbssample.SampleApplication
import com.originsdigital.hbssample.databinding.FragmentStatsBinding

class StatsFragment: Fragment() {

    private var _binging: FragmentStatsBinding? = null
    private val binding: FragmentStatsBinding
    get() = requireNotNull(_binging)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }

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