package com.originsdigital.hbssample.h2h

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.SampleApplication
import com.originsdigital.hbssample.databinding.FragmentHeadToHeadBinding

class HeadToHeadFragment : Fragment() {

    private var _binding: FragmentHeadToHeadBinding? = null
    private val binding: FragmentHeadToHeadBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.container.setBackgroundResource(SampleApplication.backgroundResId)
            }
        }
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.headToHead.setupTeamIds("1111", "2222")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadToHeadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}