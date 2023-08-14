package com.owlcode.appcomandav3.domain.zones.model

import com.google.gson.annotations.SerializedName

data class ZoneModel(
    @SerializedName("Nombre") val nombreZonas: String,
    @SerializedName("Numero") val idZona: String,
)