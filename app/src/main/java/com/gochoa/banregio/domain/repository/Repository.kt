package com.gochoa.banregio.domain.repository

import com.gochoa.banregio.data.remote.ApiResponseStatus
import com.gochoa.banregio.data.remote.response.CardInfo
import com.gochoa.banregio.data.remote.response.MovementsResponseItem

interface Repository {
    suspend fun getCardInfo(): ApiResponseStatus<CardInfo>

    suspend fun getMovements(): ApiResponseStatus<List<MovementsResponseItem>>
}