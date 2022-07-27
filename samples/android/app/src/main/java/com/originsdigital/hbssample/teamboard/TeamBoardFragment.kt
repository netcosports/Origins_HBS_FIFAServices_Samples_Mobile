package com.originsdigital.hbssample.teamboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbswidgets.android.databinding.FragmentTeamBoardBinding
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class TeamBoardFragment : Fragment() {

    private var _binding: FragmentTeamBoardBinding? = null
    private val binding: FragmentTeamBoardBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val commonTeamId = HbsSdk.getFavoriteTeamId() ?: "43946"
        binding.teamBoard.setupTeamId(commonTeamId)
        binding.teamBoard.setOnMatchClickListener(object : OnMatchClickListener {
            override fun onMatchClicked(matchId: String) {
                Toast.makeText(requireContext(), "Custom match click: $matchId", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}