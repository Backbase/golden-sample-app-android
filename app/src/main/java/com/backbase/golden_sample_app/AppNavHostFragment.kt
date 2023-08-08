package com.backbase.golden_sample_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backbase.golden_sample_app.databinding.FragmentAppNavHostBinding

/**
 * Primary navigation menu host for journeys
 */
class AppNavHostFragment : Fragment() {

    private lateinit var binding: FragmentAppNavHostBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppNavHostBinding.inflate(inflater, container, false)
        return binding.root
    }
}
