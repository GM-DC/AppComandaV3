package com.owlcode.appcomandav3.domain.users.model

data class ListUsuarioModel(
    val listUsuarioModel: List<UsuarioDC>
)

data class  UsuarioDC (
    val codUsuario: String,
    val nombreUsuario: String
)


