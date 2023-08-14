package com.owlcode.appcomandav3.data.passcode.remote

import com.owlcode.appcomandav3.data.orders.remote.dto.LoginUserDTO
import com.owlcode.appcomandav3.data.passcode.remote.dto.LoginUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginApi {

    @POST("api/Users/Login")
    suspend fun postLoginComanda(@Body loginMozo: LoginUserDTO) : LoginUserResponse

}