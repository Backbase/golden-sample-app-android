package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.backbase.golden_sample_app.databinding.FragmentHomeBinding

/**
 * Just a welcome message to whoever is logged in.
 *
 * Created by Backbase R&D B.V on 17/08/2023.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.welcomeText.text = "Welcome ${args.workspaceName}"
        return view
    }
}
