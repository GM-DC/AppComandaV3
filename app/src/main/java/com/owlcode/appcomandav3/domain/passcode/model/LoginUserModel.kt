package com.owlcode.appcomandav3.domain.passcode.model

import com.owlcode.appcomandav3.data.orders.remote.dto.LoginUserDTO

data class LoginUserModel(
    val usuariomozo: String,
    var passmozo: String,
)

fun LoginUserModel.toLoginUserDTO(): LoginUserDTO {
    return  LoginUserDTO(
        usuariomozo = usuariomozo,
        passmozo = passmozo
    )
}