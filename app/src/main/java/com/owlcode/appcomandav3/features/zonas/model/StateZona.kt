package com.owlcode.appcomandav3.features.zonas.model

import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.domain.zones.model.ZoneModel

data class StateZona (
    val listaZona: List<ZoneModel> = listOf(),
    val listaMesa: MutableList<TableModel> = mutableListOf(),
    val message: String? = null,
    val isLoading: Boolean = false,
)