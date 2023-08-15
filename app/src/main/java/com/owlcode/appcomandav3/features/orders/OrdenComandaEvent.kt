package com.owlcode.appcomandav3.features.orders

import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.domain.orders.model.OrderModel
import com.owlcode.appcomandav3.domain.orders.model.SendOrdersModel

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

    object OnClickEnviarComanda : OrdenComandaEvent()
    data class OnEnviarComanda(val pedido: SendOrdersModel) : OrdenComandaEvent()
    data class RespuestaDelEnvioComanda(val idPedido: String, val resultComandaEnviarda :SendOrdersModel?) : OrdenComandaEvent()
    data class FiltarListaParaImprimirComanda(val resp :List<OrderModel>, val resultSendOrder: SendOrdersModel) : OrdenComandaEvent()

    data class PutUpdateColorOrder(val comanda: String,val idpedido: Int) : OrdenComandaEvent()

    data class ActualizarEstadoMesa(val idZona: String,val idMesa: Int,val estadoMesa: String,val nameMozo: String) : OrdenComandaEvent()


    data class BuscarCoincidencia(val idProducto: Int) :  OrdenComandaEvent()
}


sealed class UIEvent {
    object GoToZona : UIEvent()
}