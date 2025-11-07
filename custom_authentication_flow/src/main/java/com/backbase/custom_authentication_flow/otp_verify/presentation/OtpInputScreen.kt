package com.backbase.custom_authentication_flow.otp_verify.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.backbase.custom_authentication_flow.databinding.FragmentOtpInputBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpInputScreen : Fragment() {

    private var _binding: FragmentOtpInputBinding? = null
    val binding get() = _binding!!

    private val viewModel: OtpInputViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtpInputBinding.inflate(inflater, container, false)
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
        with(binding) {
            otpInputSubmit.apply {
                setOnClickListener {
                    loading = true
                    viewModel.submit(otpEditText.text?.toString().orEmpty())
                }
            }
            otpInputResend.apply {
                setOnClickListener {
                    loading = true
                    viewModel.resend()
                }
            }
        }
    }

    private fun initObservers() {
        viewModel.action
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { handleActions(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleActions(action: OtpInputViewModel.Action) {
        when (action) {
            OtpInputViewModel.Action.PopBack -> findNavController().popBackStack()
            else -> Unit
        }
    }
}