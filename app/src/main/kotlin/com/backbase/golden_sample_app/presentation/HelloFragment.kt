package com.backbase.golden_sample_app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.backbase.golden_sample_app.databinding.FragmentHelloBinding

/**
 * Just a welcome message to whoever is logged in.
 */
class HelloFragment : Fragment() {

    private lateinit var binding: FragmentHelloBinding

    private val args: HelloFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelloBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.welcomeText.text = "Welcome ${args.workspaceName}"
        return view
    }
}
