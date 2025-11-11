package com.backbase.accounts_journey.presentation.accountlist.mapper

import com.backbase.accounts_journey.configuration.AccountsJourneyConfiguration
import com.backbase.accounts_journey.configuration.icon.IconsConfiguration
import com.backbase.accounts_journey.presentation.accountlist.model.AccountHeaderUiModel
import com.backbase.accounts_journey.presentation.accountlist.model.AccountSummaryUiModel
import com.backbase.accounts_journey.presentation.accountlist.model.AccountUiModel
import com.backbase.accounts_journey.presentation.accountlist.model.AccountsUiModel
import com.backbase.android.client.gen2.arrangementclient2.model.CreditCardProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.CurrentAccountProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.CustomProductKind
import com.backbase.android.client.gen2.arrangementclient2.model.DebitCardProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.InvestmentAccountProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.LoanProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.ProductSummary
import com.backbase.android.client.gen2.arrangementclient2.model.SavingsAccountProductKinds
import com.backbase.android.client.gen2.arrangementclient2.model.TermDepositProductKinds
import com.backbase.android.design.amount.AmountFormat
import java.math.BigDecimal
import java.util.Locale

/**
 *  A AccountSummary mapper from DTO models to UI models.
 *
 *  Created by Backbase R&D B.V on 04/10/2023.
 */
class AccountUiMapper(accountsJourneyConfiguration: AccountsJourneyConfiguration) {

    private val iconsConfiguration: IconsConfiguration by lazy {
        accountsJourneyConfiguration.iconsConfiguration
    }

    fun mapToUi(dto: ProductSummary): AccountSummaryUiModel {
        return AccountSummaryUiModel(
            customProducts = dto.customProductKinds.mapToUi(),
            currentAccounts = dto.currentAccounts?.mapToUi(),
            savingAccounts = dto.savingsAccounts?.mapToUi(),
            termDeposits = dto.termDeposits?.mapToUi(),
            loans = dto.loans?.mapToUi(),
            creditCards = dto.creditCards?.mapToUi(),
            debitCards = dto.debitCards?.mapToUi(),
            investmentAccounts = dto.investmentAccounts?.mapToUi()
        )
    }

    /**
     * An AccountSummary mapper from DTO models to UI models.
     *
     * Created by Backbase R&D B.V on 04/10/2023.
     */
    internal fun List<CustomProductKind>.mapToUi(): List<AccountsUiModel> {
        if (this.isEmpty()) return emptyList()

        return this.map { customProducts ->
            AccountsUiModel(
                AccountHeaderUiModel(name = customProducts.name),
                products = customProducts.products.map { dto ->
                    AccountUiModel(
                        id = dto.id,
                        name = dto.displayName,
                        balance = formatCurrency(dto.currency, dto.availableBalance?.toBigDecimalOrNull()),
                        state = formatState(dto.state?.state, dto.BBAN),
                        icon = iconsConfiguration.iconCustomProduct,
                        isVisible = dto.userPreferences?.visible
                    )
                }
            )
        }
    }

    internal fun CurrentAccountProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = formatCurrency(dto.currency, dto.availableBalance?.toBigDecimalOrNull()),
                    state = formatState(dto.state?.state, dto.BBAN),
                    icon = iconsConfiguration.iconCurrentAccount,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    internal fun SavingsAccountProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = formatCurrency(dto.currency, dto.availableBalance?.toBigDecimalOrNull()),
                    state = formatState(dto.state?.state, dto.BBAN),
                    icon = iconsConfiguration.iconSavingsAccount,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    internal fun TermDepositProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = formatCurrency(dto.currency, dto.principalAmount),
                    state = formatState(dto.state?.state, dto.BBAN),
                    icon = iconsConfiguration.iconTermDeposit,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    internal fun LoanProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = formatCurrency(dto.currency, dto.principalAmount),
                    state = formatState(dto.state?.state, dto.BBAN),
                    icon = iconsConfiguration.iconLoan,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    internal fun CreditCardProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = formatCurrency(dto.currency, dto.availableBalance?.toBigDecimalOrNull()),
                    state = formatState(dto.state?.state, dto.creditCardAccountNumber),
                    icon = iconsConfiguration.iconCreditCard,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    internal fun DebitCardProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = "",
                    state = formatState(dto.state?.state, dto.cardNumber.toString()),
                    icon = iconsConfiguration.iconDebitCard,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    internal fun InvestmentAccountProductKinds.mapToUi(): AccountsUiModel? {
        if (this.products.isEmpty()) return null

        return AccountsUiModel(
            AccountHeaderUiModel(name = this.name),
            products = this.products.map { dto ->
                AccountUiModel(
                    id = dto.id,
                    name = dto.displayName,
                    balance = formatCurrency(dto.currency, dto.currentInvestmentValue?.toBigDecimalOrNull()),
                    state = formatState(dto.state?.state, dto.BBAN),
                    icon = iconsConfiguration.iconInvestmentAccount,
                    isVisible = dto.userPreferences?.visible
                )
            }
        )
    }

    private fun formatCurrency(currency: String?, amount: BigDecimal?): String {
        return AmountFormat().apply {
            enableIsoFormat = true
            locale = Locale.getDefault()
            currencyCode = currency
        }.format(amount ?: BigDecimal.ZERO)
    }

    private fun formatState(state: String?, number: String?): String? {
        if (state == null || number == null) return null
        return if (state == "Active") number
        else state
    }
}
