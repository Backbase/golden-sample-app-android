package com.backbase.golden_sample_app.payments

import androidx.navigation.NavController
import com.backbase.android.Backbase
import com.backbase.android.client.gen2.arrangementclient2.api.ArrangementsApi
import com.backbase.android.client.gen2.arrangementclient2.api.ProductSummaryApi
import com.backbase.android.client.gen2.paymentordera2aclient1.api.A2aClientApi
import com.backbase.android.client.gen2.paymentorderv2client2.api.PaymentOrdersApi
import com.backbase.android.retail.journey.more.MoreJourneyScope
import com.backbase.android.retail.journey.payments.ExternalPaymentAccountsServiceUseCase
import com.backbase.android.retail.journey.payments.PaymentAccountsUseCase
import com.backbase.android.retail.journey.payments.PaymentJourneyScope
import com.backbase.android.retail.journey.payments.PaymentJourneyType
import com.backbase.android.retail.journey.payments.PaymentRouter
import com.backbase.android.retail.journey.payments.PaymentUseCase
import com.backbase.android.retail.journey.payments.configuration.a2aPaymentConfiguration
import com.backbase.android.retail.journey.payments.gen2_paymentorder_v2_client_2.PaymentOrderV2Client2PaymentServiceUseCase
import com.backbase.android.retail.journey.payments.gen_arrangements_client_2.ArrangementsClient2PaymentAccountsUseCase
import com.backbase.android.retail.journey.payments.gen_paymentordera2a_client_1.PaymentOrderA2AClient1ServiceUseCase
import com.backbase.golden_sample_app.Sdk
import com.backbase.golden_sample_app.router.MoreMenuRouterImpl
import org.koin.dsl.module

/**
 * Created by Backbase R&D B.V. on 23/01/2024.
 */
internal fun paymentsMenuModule(
    navController: NavController
) = module {
    scope<MoreJourneyScope> {
        factory(paymentsScopeId) {
            MoreMenuRouterImpl(navController)
        }

        scoped(paymentsScopeId) { paymentsMenuConfig(get()) }
    }

    scope<PaymentJourneyScope> {
        scoped { Sdk.moshi }
        scoped { Sdk.responseBodyParser }
        scoped(PaymentJourneyType.A2A) { a2aPaymentConfiguration() }

        scoped<PaymentRouter> { // Required
            object : PaymentRouter {
                override fun onExit(stepNavController: NavController) {
                    navController.popBackStack()
                }
            }
        }

        // Use-cases
        scoped<PaymentAccountsUseCase> {
            ArrangementsClient2PaymentAccountsUseCase(
                Backbase.requireInstance().getClient(ProductSummaryApi::class.java),
                Backbase.requireInstance().getClient(ArrangementsApi::class.java)
            )
        }

        scoped<PaymentUseCase> {
            PaymentOrderV2Client2PaymentServiceUseCase(
                Backbase.requireInstance().getClient(PaymentOrdersApi::class.java)
            )
        }

        scoped<ExternalPaymentAccountsServiceUseCase> {
            PaymentOrderA2AClient1ServiceUseCase(
                Backbase.requireInstance().getClient(A2aClientApi::class.java)
            )
        }
    }
}
