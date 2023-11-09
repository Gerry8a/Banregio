package com.gochoa.banregio.data.remote

import com.gochoa.banregio.data.remote.response.CardInfo
import com.gochoa.banregio.data.remote.response.MovementsResponseItem
import com.gochoa.banregio.data.utils.Dictionary.CARD_INFO
import com.gochoa.banregio.data.utils.Dictionary.MOVEMENTS
import retrofit2.http.GET

interface Api {
    @GET("$CARD_INFO")
    suspend fun getCard(): CardInfo

    @GET("$MOVEMENTS")
    suspend fun getMovements(): List<MovementsResponseItem>
}