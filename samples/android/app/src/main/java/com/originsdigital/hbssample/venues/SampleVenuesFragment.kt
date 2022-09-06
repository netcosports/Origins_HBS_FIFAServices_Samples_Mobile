package com.originsdigital.hbssample.venues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbswidgets.android.R
import com.originsdigital.hbswidgets.android.databinding.FragmentVenuesBinding
import com.originsdigital.hbswidgets.core.HbsSdk
import com.originsdigital.hbswidgets.venue.VenuesWidget

class SampleVenuesFragment : BaseSampleFragment<FragmentVenuesBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVenuesBinding {
        return FragmentVenuesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val widget = HbsSdk.venuesWidget(view.context)
        widget.venueOnClickListener = object : VenuesWidget.VenueOnClickListener {
            override fun onVenueClick(venueId: String) {
                findNavController().navigate(
                    R.id.action_venueFragment_to_venieDetailsFragment,
                    SampleVenueDetailsFragment.buildArgs(venueId)
                )
            }

        }

        binding.venuesContainer.addView(
            widget,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}