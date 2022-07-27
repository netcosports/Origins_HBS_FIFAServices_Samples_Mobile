package com.originsdigital.hbssample.championship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.databinding.FragmentChampionshipBinding
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class SampleChampionshipFragment : Fragment() {

    private var _binding: FragmentChampionshipBinding? = null
    private val binding: FragmentChampionshipBinding
        get() = requireNotNull(_binding)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChampionshipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}