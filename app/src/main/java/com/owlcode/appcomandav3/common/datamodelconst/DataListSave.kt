package com.owlcode.appcomandav3.common.datamodelconst

import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel

data class DataListSave(
    val idZona: String,
    val idMesa: Int,
    val listPedido : MutableList<ListOrdersModel> = mutableListOf(),
)