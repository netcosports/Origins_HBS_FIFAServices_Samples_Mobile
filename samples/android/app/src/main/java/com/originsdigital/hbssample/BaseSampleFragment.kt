package com.originsdigital.hbssample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.originsdigital.hbssample.settings.SettingsActivity

abstract class BaseSampleFragment<VIEW_BINDING : ViewBinding> : Fragment() {

    private var _binding: VIEW_BINDING? = null
    protected val binding: VIEW_BINDING
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createViewBinding(inflater, container)
        return binding.root
    }

    val needLocalMatchListener: Boolean
        get() = SettingsActivity.getMatchClickHandler(requireContext()) == SettingsActivity.MatchClickHandler.LOCAL

    abstract fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VIEW_BINDING

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}