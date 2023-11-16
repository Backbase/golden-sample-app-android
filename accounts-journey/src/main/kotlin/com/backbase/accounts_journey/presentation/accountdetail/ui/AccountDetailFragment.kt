package com.backbase.accounts_journey.presentation.accountdetail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.backbase.accounts_journey.databinding.FragmentAccountDetailBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

/**
 * The Fragment of the account detail.
 *
 * Created by Backbase R&D B.V on 16/11/2023.
 */
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
        binding.contentLoading.visibility =
            if (uiState.isLoading) ProgressBar.VISIBLE else ProgressBar.GONE

        val context = this.requireContext()
        if (uiState.accountDetail != null) {
            val uiModel = uiState.accountDetail
            binding.apply {
                accountIcon.icon = context.getDrawable(uiModel.icon)
                headerAccount.text = uiModel.name
                headerBban.text = uiModel.BBAN
                headerBalance.text = uiModel.availableBalance

                accountDetailsAccountHolderNames.text = uiModel.accountHolderNames
                accountDetailsAccountNumber.text = uiModel.BBAN

                generalAccountType.text = uiModel.productKindName
                generalAccountName.text = uiModel.name

                uiModel.bankBranchCode?.let {
                    generalAbaRoutingNumber.text = uiModel.bankBranchCode
                } ?: run {
                    generalAbaRoutingNumberLabel.visibility = View.GONE
                    generalAbaRoutingNumber.visibility = View.GONE
                }

                generalTimeOfLastUpdate.text = uiModel.lastUpdateDate

                uiModel.accountInterestRate?.let {
                    interestDetailsInterestRate.text = uiModel.accountInterestRate
                } ?: run {
                    interestDetailsInterestRateLabel.visibility = View.GONE
                    interestDetailsInterestRate.visibility = View.GONE
                }
                interestDetailsAccuredInterest.text = uiModel.accruedInterest

                overdraftDetailsOverdraftLimit.text = uiModel.creditLimit

                otherAccountOpeningDate.text = uiModel.accountOpeningDate

                contentError.visibility = View.GONE
                contentMain.visibility = View.VISIBLE
            }
        } else if (uiState.error != null) {
            binding.apply {
                contentMain.visibility = View.GONE
                contentError.visibility = View.VISIBLE
                errorText.text = requireContext().getText(uiState.error)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
