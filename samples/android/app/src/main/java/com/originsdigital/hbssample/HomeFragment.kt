package com.originsdigital.hbssample

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.databinding.FragmentHomeBinding
import com.originsdigital.hbssample.settings.SettingsActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {

                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }

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
        binding.headToHead.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_headToHeadFragment)
        }

        binding.injures.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_injuresFragment)
        }

        binding.actions.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_actionsFragment)
        }

        binding.venues.setOnClickListener {
            findNavController().navigate(R.id.action_home_fragment_to_venuesFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}