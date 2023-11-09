package com.gochoa.banregio.domain.repository

import com.gochoa.banregio.data.remote.ApiResponseStatus
import com.gochoa.banregio.data.remote.response.CardInfo

interface Repository {
    suspend fun getCardInfo(): ApiResponseStatus<CardInfo>
}