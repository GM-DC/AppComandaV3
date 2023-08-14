package com.owlcode.appcomandav3.features.orders

import com.owlcode.appcomandav3.domain.orders.model.DishModel

sealed class OrdenComandaEvent {
    object InitCategoria :  OrdenComandaEvent()

    data class InitPlatos(val nameCategoria: String) :  OrdenComandaEvent()
    data class InitPedidoDespachado(val idPedido: String) :  OrdenComandaEvent()
    data class InitPreCuenta(val idPedido: String) :  OrdenComandaEvent()
    data class InitDataObservacion (val position: Int) :  OrdenComandaEvent()

    data class OnClickAddProducto(val platoAgregado: DishModel) :  OrdenComandaEvent()
    data class OnClickAumentarProducto(val position: Int) :  OrdenComandaEvent()
    data class OnClickDisminuirProducto(val position: Int) :  OrdenComandaEvent()
    data class OnSwipeDelete(val position: Int) :  OrdenComandaEvent()
    data class OnClickAgregarNotaProducto(val position: Int, val textObservacion: String ) :  OrdenComandaEvent()

    object OnClickLimpiar : OrdenComandaEvent()
    object OnClickPreCuenta : OrdenComandaEvent()

    data class BuscarCoincidencia(val idProducto: Int) :  OrdenComandaEvent()
}