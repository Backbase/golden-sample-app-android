package resources

import com.backbase.domain.model.product_summary.AccountSummary
import com.backbase.domain.model.product_summary.MaskableAttribute
import com.backbase.domain.model.product_summary.UserPreferences
import com.backbase.domain.model.product_summary.common.ProductState
import com.backbase.domain.model.product_summary.current_accounts.CurrentAccount
import com.backbase.domain.model.product_summary.current_accounts.CurrentAccounts
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.ZoneOffset

val TEST_ACCOUNTS = AccountSummary {
    currentAccounts = CurrentAccounts {
        products = listOf(
            CurrentAccount {
                debitCardItems = emptySet()
                bookedBalance = "-78861.9800000000"
                availableBalance = "45.89"
                creditLimit = "551055.50000"
                BBAN = "*********************0025"
                BIC = "WFBIUS6S"
                unMaskableAttributes = setOf(MaskableAttribute.BBAN)
                currency = "USD"
                bankBranchCode = "111000025"
                bankBranchCode2 = null
                accountInterestRate = BigDecimal.ONE
                creditLimitUsage = BigDecimal.TEN
                creditLimitInterestRate = BigDecimal.ONE
                creditLimitExpiryDate = OffsetDateTime.of(2029, 12, 21, 0, 0, 0, 0, ZoneOffset.UTC)
                accruedInterest = BigDecimal.ZERO
                accountHolderNames = "Paolo Doe"
                startDate = OffsetDateTime.now()
                creditAccount = true
                debitAccount = true
                id = "1f6cf34a-c3fc-48f6-8e2c-e958c7f6e9c3"
                name = "TESTDATA"
                externalTransferAllowed = true
                crossCurrencyAllowed = true
                productKindName = "Current Account"
                productTypeName = "Current Account"
                bankAlias = "Paolo's Current Account"
                accountOpeningDate = OffsetDateTime.now()
                lastUpdateDate = OffsetDateTime.now()
                userPreferences = UserPreferences {
                    alias = "Paolo’s Current Test"
                    visible = true
                    favorite = false
                }
                state = ProductState {
                    externalStateId = "Active"
                    state = "Active"
                }
                displayName = "Alpha"
            }
        )
        name = "Current Accounts"
    }
}
