package com.gochoa.banregio.data.remote.response

import com.squareup.moshi.Json

data class CardInfo(
    @field: Json(name = "CVV") val cvv: Int,
    @field: Json(name = "Fecha_Exp") val fechaExp: String,
    @field: Json(name = "Monto") val monto: Int,
    @field: Json(name = "Nombre_Banco") val nombreBanco: String,
    @field: Json(name = "Numero_Tarjeta") val numeroTarjeta: String,
    @field: Json(name = "Titular_Tarjeta") val titularTarjeta: String,
    @field: Json(name = "pkTarjetaCreditoID") val tarjetaCreditoId: Int
){

}