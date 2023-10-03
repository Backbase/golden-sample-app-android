package com.backbase.accounts_journey.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.backbase.accounts_journey.R
import com.backbase.accounts_journey.databinding.FragmentAccountListBinding
import com.backbase.accounts_journey.presentation.viewmodel.AccountListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AccountListViewModel by viewModel()

    @Suppress("VariableNaming")
    private val MAX_POOL_SIZE: Int = 15

    private val accountListAdapter: AccountListAdapter = AccountListAdapter(
        onClick = { itemClicked(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountListBinding.inflate(inflater, container, false)

        val recyclerView = binding.accountlist
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = accountListAdapter
            recycledViewPool.setMaxRecycledViews(R.layout.account_list_item, MAX_POOL_SIZE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        } else if (uiState.accountSummary.isEmpty() && !uiState.isLoading) {
            accountListAdapter.submitList(emptyList())
            binding.noAccountImage.icon = ContextCompat.getDrawable(
                requireContext(),
                com.backbase.android.design.R.drawable.backbase_ic_no_accounts
            )
            binding.accountsResultText.text = requireContext().getText(R.string.no_accounts)
            binding.noAccountsGroup.visibility = View.VISIBLE
        }

        if (uiState.error != null) {
            binding.noAccountImage.icon = ContextCompat.getDrawable(
                requireContext(),
                com.backbase.android.design.R.drawable.backbase_ic_error
            )
            binding.accountsResultText.text = requireContext().getText(uiState.error)
            binding.noAccountsGroup.visibility = View.VISIBLE
        }
    }

    private fun itemClicked(id: String) {
        // TODO: navigate to details screen
        println("tapped on $id")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
