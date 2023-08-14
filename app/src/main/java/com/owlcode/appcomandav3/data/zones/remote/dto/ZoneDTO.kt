package com.owlcode.appcomandav3.data.zones.remote.dto

import com.owlcode.appcomandav3.domain.zones.model.ZoneModel
import com.google.gson.annotations.SerializedName

data class ZoneDTO (
    @SerializedName("Nombre") val nombreZonas: String,
    @SerializedName("Numero") val idZona: String,
)

fun ZoneDTO.toZoneModel(): ZoneModel {
    return  ZoneModel(
        nombreZonas = nombreZonas,
        idZona = idZona
    )
}