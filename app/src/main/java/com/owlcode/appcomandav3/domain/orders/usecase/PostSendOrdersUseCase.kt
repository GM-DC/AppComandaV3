package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.repositories.SendOrdersRepository
import com.owlcode.appcomandav3.domain.orders.model.SendOrdersModel
import javax.inject.Inject

class PostSendOrdersUseCase @Inject constructor(
    private val sendOrdersRepository : SendOrdersRepository
) {

    suspend operator fun invoke(orders: SendOrdersModel): NetworkResult<SendOrdersModel> {
        return sendOrdersRepository.postSendOrders(orders)
    }
}