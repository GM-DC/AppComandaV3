package com.owlcode.appcomandav3.data.orders.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.OrderDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderApi {

    @GET("api/Pedido/Comanda")
    suspend fun getComanda(@Query("idPedido") idPedido:String) : List<OrderDTO>

}