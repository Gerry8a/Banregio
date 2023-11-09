package com.gochoa.banregio.domain.repository

import com.gochoa.banregio.data.remote.Api
import com.gochoa.banregio.data.remote.ApiResponseStatus
import com.gochoa.banregio.data.remote.makeNetworkCall
import com.gochoa.banregio.data.remote.response.CardInfo
import javax.inject.Inject


class RepositoryImp @Inject constructor(
    private val api: Api
): Repository {

    override suspend fun getCardInfo(): ApiResponseStatus<CardInfo> = makeNetworkCall {
        api.getCard()
    }
}