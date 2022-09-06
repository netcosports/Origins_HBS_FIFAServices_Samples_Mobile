package com.originsdigital.hbssample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.settings.SettingsActivity
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentHomeBinding

class SampleHomeFragment : BaseSampleFragment<FragmentHomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.groupStandings.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_groupStandingRootFragment)
        }

        binding.toolbar.setNavigationOnClickListener {
            startActivity(SettingsActivity.getLaunchIntent(it.context))
        }

        binding.stats.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_statsFragment)
        }

        binding.matches.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_groupMatchesFragment)
        }

        binding.championship.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_topChampionshipFragment)
        }

        binding.videos.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_topVideosFragment)
        }

        binding.favorites.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_favoritesFragment)
        }
        binding.teams.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_teamsFragment)
        }

        binding.teamboard.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_teamBoardFragment)
        }

        binding.headToHead.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_headToHeadTypeFragment)
        }

        binding.actions.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_actionsFragment)
        }

        binding.venues.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_venuesFragment)
        }

        binding.watch.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_groupWatchesFragment)
        }

        binding.matchCenter.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_matchCenterFragment)
        }

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

}