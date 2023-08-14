package com.owlcode.appcomandav3.domain.zones.model

data class TableModel(
    val idMesa: Int,
    val estado: String,
    val estadoTrans: String,
    val idZona: String,
    val secuencia: Int?,
    val tipo: String,
    val idPedido: String?,
    val NombreMozo: String?
)
