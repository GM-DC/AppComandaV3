package com.owlcode.appcomandav3.features.login.model

import com.owlcode.appcomandav3.domain.passcode.model.LoginUserResponseModel
import com.owlcode.appcomandav3.domain.users.model.UsuarioDC

data class StateLogin(
    val listUsers : List<UsuarioDC> = listOf(),
    val user : String = "",
    val password : String = "",
    val stateButton : Boolean = false,
    val isLoading : Boolean = false,
    val message : String? = null,
    val responseLogin: LoginUserResponseModel? = null
)
