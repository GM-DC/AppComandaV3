package com.owlcode.appcomandav3.data.orders.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.PreCountDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PreCountApi {

    @GET("api/Pedido/Precuenta")
    suspend fun getPreCuenta(@Query("idPedido") idPedido:String) : PreCountDTO

}