package com.originsdigital.hbssample.stats

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.SampleApplication
import com.originsdigital.hbssample.databinding.FragmentStatsTeamMatchesBinding

class TeamMatchesStatsFragment : Fragment() {

    private var _binding: FragmentStatsTeamMatchesBinding? = null
    private val binding: FragmentStatsTeamMatchesBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.teamMatchesWidget.setupParams(teamId = "275075")
        binding.teamMatchesWidgetTransparent.setupParams(teamId = "275075")
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsTeamMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}