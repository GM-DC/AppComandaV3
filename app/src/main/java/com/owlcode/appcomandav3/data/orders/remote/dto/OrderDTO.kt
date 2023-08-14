package com.owlcode.appcomandav3.data.orders.remote.dto

import com.owlcode.appcomandav3.domain.orders.model.OrderDetailModel
import com.owlcode.appcomandav3.domain.orders.model.OrderModel

data class OrderDTO(
    val numerO_PEDIDO: String,
    val destino: String,
    val zona: String,
    val mesa: String,
    val mesero: String,
    val rutacomanda: String,
    val fechayhora: String,
    val detalle: List<OrderDetailDTO>
){
    fun toOrderModel() = OrderModel(
        numerO_PEDIDO= numerO_PEDIDO ,
        destino= destino,
        zona= zona,
        mesa=mesa ,
        mesero= mesero,
        rutacomanda= rutacomanda,
        fechayhora= fechayhora,
        detalle= detalle.map { it.toOrderDetailModel() } )
}

data class OrderDetailDTO(
    val iD_PRODUCTO: Int,
    val producto: String,
    val cantidad: Int,
    val precio: Double,
    val importe: Double,
    val observacion: String,
    val noM_IMP: String?,
    val secuencia: Int
){
    fun toOrderDetailModel() = OrderDetailModel(
        iD_PRODUCTO= iD_PRODUCTO,
        producto= producto,
        cantidad= cantidad,
        precio= precio,
        importe= importe,
        observacion= observacion,
        noM_IMP=  noM_IMP,
        secuencia= secuencia)
}