package com.originsdigital.hbssample.videos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.databinding.FragmentSampleVideosBinding
import com.originsdigital.hbswidgets.video.VideoWidgetCategory

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
        binding.widget1.setCategory(VideoWidgetCategory.MATCH_FEEDS)
        binding.widget2.setCategory(VideoWidgetCategory.MATCH_CLIPS)
//        binding.widget3.setCategory("Matchday-1")
//        binding.widget4.setCategory("Interviews")
    }
}