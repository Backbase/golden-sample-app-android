package com.backbase.cards_journey.impl.domain

import com.backbase.android.client.cardsclient2.model.CardItem
import com.backbase.android.clients.common.CallResult
import com.backbase.android.clients.common.coroutines.executeAsSuspended
import com.backbase.api.data.CustomCardClient
import com.backbase.api.domain.CustomCardUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomCardUseCaseImpl(private val customCardClient: CustomCardClient) : CustomCardUseCase {
    override suspend fun getCards(): Result<List<CardItem>> {
        return withContext(Dispatchers.IO) {
            when (
                val callResult =
                    customCardClient.getCards().executeAsSuspended()
            ) {
                is CallResult.Success -> {
                    Result.success(callResult.data)
                }

                is CallResult.Error -> {
                    Result.failure(
                        Throwable(
                            message = callResult.errorResponse.errorMessage,
                            cause = Throwable(callResult.errorResponse.causeTrace),
                        )
                    )
                }

                else -> {
                    Result.failure(Throwable())
                }
            }
        }
    }

    override suspend fun getCardDetails(id: String): Result<CardItem> {
        return withContext(Dispatchers.IO) {
            when (
                val callResult =
                    customCardClient.getCardDetails(id).executeAsSuspended()
            ) {
                is CallResult.Success -> {
                    Result.success(callResult.data)
                }

                is CallResult.Error -> {
                    Result.failure(
                        Throwable(
                            message = callResult.errorResponse.errorMessage,
                            cause = Throwable(callResult.errorResponse.causeTrace),
                        )
                    )
                }

                else -> {
                    Result.failure(Throwable())
                }
            }
        }
    }
}
