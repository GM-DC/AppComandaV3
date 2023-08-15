package com.owlcode.appcomandav3.features.orders.model

import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.domain.orders.model.PreCount
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.domain.zones.model.ZoneModel

data class StateOrden (
    val listCategoria : List<CategoryModel> = listOf(),
    val listPlatos : List<DishModel> = listOf(),
    val listPedido : MutableList<ListOrdersModel> = mutableListOf(),
    val listPreCuenta : PreCount? = null,
    val datoMesa : TableModel? = null,
    val datoZone : ZoneModel? = null,
    val coincidencia : List<Int> = listOf(),
    val isLoading : Boolean = false,
    val message: String? = null,
    val inputText : String = "",
)