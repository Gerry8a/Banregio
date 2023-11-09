package com.gochoa.banregio.data.remote

import com.gochoa.banregio.data.remote.response.CardInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("{1}")
    suspend fun getCard(): CardInfo
}