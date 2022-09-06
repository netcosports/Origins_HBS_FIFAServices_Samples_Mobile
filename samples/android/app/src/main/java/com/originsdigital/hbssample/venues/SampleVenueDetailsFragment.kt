package com.originsdigital.hbssample.venues

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.originsdigital.hbssample.BaseSampleFragment
import com.originsdigital.hbssample.matchcenter.SampleMatchCenterActivity
import com.originsdigital.hbswidgets.android.databinding.SampleFragmentVenueDetailsBinding
import com.originsdigital.hbswidgets.matchcenter.HbsMatchClickableWidget
import com.originsdigital.hbswidgets.matchcenter.OnMatchClickListener

class SampleVenueDetailsFragment: BaseSampleFragment<SampleFragmentVenueDetailsBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        val venueId = requireNotNull(arguments?.getString(VENUE_ID))
        binding.venueDetails.setupVenueId(venueId)
        if (needLocalMatchListener) {
            binding.venueDetails.hbsMatchClickListener = object : OnMatchClickListener {
                override fun onMatchClicked(context: Context, matchId: String) {
                    SampleMatchCenterActivity.launch(context, matchId = matchId, isLocal = true)
                }
            }
        }
    }
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SampleFragmentVenueDetailsBinding {
        return SampleFragmentVenueDetailsBinding.inflate(inflater, container, false)
    }

    companion object {
        private const val VENUE_ID = "venue_id"
        fun buildArgs(venueId: String): Bundle {
            val bundle = Bundle()
            bundle.putString(VENUE_ID, venueId)
            return bundle
        }
    }
}