package com.backbase.accounts_journey.presentation.accountdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.backbase.accounts_journey.databinding.FragmentAccountDetailBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class AccountDetailFragment : Fragment() {

    private var _binding: FragmentAccountDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountDetailViewModel by inject()

    private val args: AccountDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { handleUiState(it) }
            .launchIn(lifecycleScope)

        viewModel.onEvent(AccountDetailEvent.OnGetAccountDetail(args.id))
    }

    private fun handleUiState(uiState: AccountDetailScreenState) {
        if (uiState.accountDetail != null) {
            val uiModel = uiState.accountDetail
            binding.apply {
                accountDetailName.text = uiModel.name
            }
        } else {
            // TODO: show error
            println("some error")
            println(uiState.error)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
