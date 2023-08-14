package com.owlcode.appcomandav3.data.orders.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.SendOrdersDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface SendOrdersApi {

    @POST("api/Pedido/CreateOrder")
    suspend fun postSendOrders(@Body ordenPedido: SendOrdersDTO) : SendOrdersDTO

}