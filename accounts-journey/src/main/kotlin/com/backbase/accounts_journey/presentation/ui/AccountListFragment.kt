package com.backbase.accounts_journey.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backbase.accounts_journey.databinding.FragmentAccountListBinding
import com.backbase.accounts_journey.presentation.viewmodel.AccountListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountListFragment : Fragment() {

    private lateinit var binding: FragmentAccountListBinding

    private val viewModel: AccountListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAccounts()
    }
}
