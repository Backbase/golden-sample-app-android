package com.backbase.accounts_journey.presentation.accountlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.accountlist.AccountListScreenConfiguration
import com.backbase.accounts_journey.databinding.FragmentAccountListBinding
import com.backbase.accounts_journey.presentation.accountdetail.ui.AccountDetailFragment.Companion.ACCOUNT_ID_ARGUMENT_KEY
import com.backbase.accounts_journey.routing.AccountsRouting
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The Fragment of the account list.
 *
 * Created by Backbase R&D B.V on 04/10/2023.
 */
class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!
    private val routing: AccountsRouting by inject()
    private val journeyConfiguration: AccountsJourneyConfiguration by inject()
    private val screenConfiguration: AccountListScreenConfiguration by lazy {
        journeyConfiguration.accountListScreenConfiguration
    }

    private val viewModel: AccountListViewModel by viewModel()

    private val accountListAdapter: AccountListAdapter = AccountListAdapter(
        onClick = { itemClicked(it) }
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountListBinding.inflate(inflater, container, false)

        val recyclerView = binding.accountlist
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = accountListAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.header.text = requireContext().getText(screenConfiguration.screenTitle)

        binding.accountlistSwipeContainer.setOnRefreshListener {
            viewModel.onEvent(AccountListEvent.OnRefresh)
        }

        binding.searchTextInput.addTextChangedListener { text ->
            viewModel.onEvent(AccountListEvent.OnSearch(text.toString()))
        }

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { handleUiState(it) }
            .launchIn(lifecycleScope)
        viewModel.onEvent(AccountListEvent.OnGetAccounts)
    }

    private fun handleUiState(uiState: AccountListScreenState) {
        binding.accountlistSwipeContainer.isRefreshing = uiState.isLoading

        if (uiState.accountSummary.isNotEmpty()) {
            binding.noAccountsGroup.visibility = View.GONE
            accountListAdapter.submitList(uiState.accountSummary)
        } else if (!uiState.isLoading) {
            accountListAdapter.submitList(emptyList())
            binding.noAccountImage.icon = ContextCompat.getDrawable(
                requireContext(),
                com.backbase.android.design.R.drawable.backbase_ic_no_accounts
            )
            binding.accountsResultText.text = requireContext().getText(R.string.no_accounts)
            binding.noAccountsGroup.visibility = View.VISIBLE
        }

        if (uiState.error != null) {
            accountListAdapter.submitList(emptyList())
            binding.noAccountImage.icon = ContextCompat.getDrawable(
                requireContext(),
                com.backbase.android.design.R.drawable.backbase_ic_error
            )
            binding.accountsResultText.text = requireContext().getText(uiState.error)
            binding.noAccountsGroup.visibility = View.VISIBLE
        }
    }
    private fun itemClicked(id: String) {
        findNavController().navigate(
            routing.onAccountSelected(),
            bundleOf(ACCOUNT_ID_ARGUMENT_KEY to id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
