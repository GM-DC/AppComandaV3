package com.owlcode.appcomandav3.data.zones.remote

import com.owlcode.appcomandav3.data.zones.remote.dto.ZoneDTO
import retrofit2.http.GET

interface ZoneApi {

    @GET("api/TablasBasicas/Detail?filter=codigo eq 'CDG_PISO'&select=nombre,numero")
    suspend fun getZones(): List<ZoneDTO>
}