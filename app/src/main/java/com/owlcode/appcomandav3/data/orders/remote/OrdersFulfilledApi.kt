package com.owlcode.appcomandav3.data.orders.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.OrdersFulfilledDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface OrdersFulfilledApi {

    @GET("/api/Pedido/PedidoMesa/{id}")
    suspend fun getOrdersFulfilled(@Path("id") id:String) : List<OrdersFulfilledDTO>

}