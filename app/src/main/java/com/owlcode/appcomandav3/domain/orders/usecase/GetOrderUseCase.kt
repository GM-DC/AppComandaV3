package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.OrderModel
import com.owlcode.appcomandav3.domain.orders.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository : OrderRepository
) {

    operator fun invoke(idpedido:String): Flow<NetworkResult<List<OrderModel>>> {
        return orderRepository.getOrder(idpedido)
    }

}
