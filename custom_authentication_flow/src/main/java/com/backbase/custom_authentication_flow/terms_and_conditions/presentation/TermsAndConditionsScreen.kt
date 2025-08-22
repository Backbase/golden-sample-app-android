package com.backbase.custom_authentication_flow.terms_and_conditions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.backbase.custom_authentication_flow.databinding.FragmentTermsConditionsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class TermsAndConditionsScreen : Fragment() {

    private var _binding: FragmentTermsConditionsBinding? = null
    val binding get() = _binding!!

    private val viewModel: TermsAndConditionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTermsConditionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.accept.apply {
            setOnClickListener {
                loading = true
                viewModel.accept()
            }
        }
    }

    private fun initObservers() {
        viewModel.action
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { handleActions(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleActions(action: TermsAndConditionsViewModel.Action) {
        when (action) {
            TermsAndConditionsViewModel.Action.PopBack -> findNavController().popBackStack()
            else -> Unit
        }
    }
}