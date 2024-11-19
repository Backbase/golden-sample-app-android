package com.backbase.cards_journey.impl.utils

import android.content.Context
import com.backbase.android.Backbase
import com.backbase.android.common.utils.dbs.CustomNetworkDBSProvider
import com.backbase.android.common.utils.dbs.DBSDataImpl
import com.backbase.android.dbs.DBSClient
import com.backbase.android.dbs.DBSDataProvider
import java.net.URI

open class BBDbsClient(
    val context: Context,
    var serverUri: URI,
    var provider: DBSDataProvider = CustomNetworkDBSProvider(context),
    var backbase: Backbase = requireNotNull(Backbase.getInstance()) { "The Backbase instance must not be null!" }
) : DBSClient, DBSDataImpl(backbase) {
    override fun setBaseURI(baseUri: URI) {
        serverUri = baseUri
    }

    override fun getBaseURI() = this.serverUri

    override fun setDataProvider(provider: DBSDataProvider?) {
        this.provider = requireNotNull(provider) { "The provider must not be null!" }
    }

    override fun getDataProvider(): DBSDataProvider = provider
}
