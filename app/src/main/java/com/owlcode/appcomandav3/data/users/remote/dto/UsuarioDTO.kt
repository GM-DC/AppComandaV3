package com.owlcode.appcomandav3.data.users.remote.dto

import com.owlcode.appcomandav3.domain.users.model.UsuarioDC


data class UsuarioDTO(
    val Codigo: String,
    val Nombre: String
)

fun UsuarioDTO.toUsuarioDC(): UsuarioDC {
    return  UsuarioDC(
        codUsuario = Codigo,
        nombreUsuario = Nombre
    )
}


