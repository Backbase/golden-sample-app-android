package com.backbase.golden_sample_app.extend_journey.contacts.presentation.contactlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.backbase.app_common.AppRouting
import com.backbase.golden_sample_app.R
import com.backbase.golden_sample_app.databinding.FragmentCustomContactsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomContactsFragment : Fragment() {

    private var _binding: FragmentCustomContactsBinding? = null
    private val binding get() = _binding!!

    private val navigator: AppRouting by inject()

    private val viewModel: CustomContactsViewModel by viewModel()

    private val contactsListAdapter: CustomContactsListAdapter = CustomContactsListAdapter {
        itemClicked(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomContactsBinding.inflate(inflater, container, false)

        binding.contactlist.apply {
            setHasFixedSize(true)
            adapter = contactsListAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
        }

        binding.searchTextInput.addTextChangedListener { text ->
            viewModel.onEvent(CustomContactsEvent.OnGetContacts(text.toString()))
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.upcoming_fragment)
        }

        binding.contactlist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.onEvent(CustomContactsEvent.OnGetContacts(binding.searchTextInput.text.toString()))
                }
            }
        })

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { handleUiState(it) }
            .launchIn(lifecycleScope)

        viewModel.onEvent(CustomContactsEvent.OnGetContacts())
    }

    private fun handleUiState(uiState: CustomContactsScreenState) {
        if (uiState.contacts.isNotEmpty()) {
            contactsListAdapter.submitList(uiState.contacts)
        } else if (!uiState.isLoading) {
            contactsListAdapter.submitList(emptyList())
        }

        if (uiState.error != null) {
            contactsListAdapter.submitList(emptyList())
        }
    }

    private fun itemClicked(id: String) {
        navigator.getNavController()?.navigate(
            CustomContactsFragmentDirections.actionCustomContactsFragmentToCustomContactDetailsFragment(id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
