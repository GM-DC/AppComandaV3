package com.owlcode.appcomandav3.data.orders.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.DishDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DishApi {
    @GET("/{nombrecategoria},{moneda}")
    suspend fun getDishes(@Path("nombrecategoria") nombrecategoria:String, @Path("moneda") moneda:String) : List<DishDTO>
}