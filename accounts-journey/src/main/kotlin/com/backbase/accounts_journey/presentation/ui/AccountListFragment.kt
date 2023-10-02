package com.backbase.accounts_journey.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    private lateinit var swipeContainer: SwipeRefreshLayout

    private val accountListAdapter: AccountListAdapter = AccountListAdapter(
        onClick = { itemClicked(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountListBinding.inflate(inflater, container, false)
        val view = binding.root

        swipeContainer = binding.accountlistSwipeContainer
        swipeContainer.setOnRefreshListener {
            viewModel.onEvent(AccountListEvent.OnGetAccounts)
        }

        val searchView = binding.searchTextInput
        searchView.addTextChangedListener { text ->
            viewModel.onEvent(AccountListEvent.OnSearch(text.toString()))
        }

        val recyclerView = binding.accountlist
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = accountListAdapter
            recycledViewPool.setMaxRecycledViews(R.layout.account_list_item, MAX_POOL_SIZE)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { handleUiState(it) }
            .launchIn(lifecycleScope)
        viewModel.onEvent(AccountListEvent.OnGetAccounts)
    }

    private fun handleUiState(uiState: AccountListScreenState) {
        when {
            uiState.isLoading -> {
                println("is loading, show a shimmer")
            }

            uiState.accountSummary.isNotEmpty() -> {
                accountListAdapter.submitList(uiState.accountSummary)
                swipeContainer.isRefreshing = false
            }

            uiState.error != null -> {
                swipeContainer.isRefreshing = false
            }
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
