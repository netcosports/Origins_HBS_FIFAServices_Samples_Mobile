package com.originsdigital.hbssample.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentSampleVideosBinding

class SampleVideosFragment : BaseSampleFragment<FragmentSampleVideosBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSampleVideosBinding {
        return FragmentSampleVideosBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.widget1.setCategory("Match%20Feed/BIF")
        binding.widget2.setCategory("Match%20Clip/Clips%20Action")
    }
}