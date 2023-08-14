package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.SendOrdersModel

interface SendOrdersRepository {

    suspend fun postSendOrders(orders: SendOrdersModel): NetworkResult<SendOrdersModel>

}