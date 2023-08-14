package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrder(idPedido:String): Flow<NetworkResult<List<OrderModel>>>

}