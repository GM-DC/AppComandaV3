package com.owlcode.appcomandav3.data.users.remote

import com.owlcode.appcomandav3.data.users.remote.dto.UsuarioDTO
import retrofit2.http.GET

interface UsuarioApi {

    @GET("api/Users?select=nombre,codigo")
    suspend fun getUsuarios(): List<UsuarioDTO>
}