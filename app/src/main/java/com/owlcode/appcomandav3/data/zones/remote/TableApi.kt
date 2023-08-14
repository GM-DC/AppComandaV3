package com.owlcode.appcomandav3.data.zones.remote


import com.owlcode.appcomandav3.data.zones.remote.dto.TableDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TableApi {

    @GET("api/Mesas")
    suspend fun getTable(@Query("filter") filter:String) : List<TableDTO>
}