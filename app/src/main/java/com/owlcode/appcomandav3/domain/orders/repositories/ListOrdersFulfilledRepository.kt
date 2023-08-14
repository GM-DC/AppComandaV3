package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import kotlinx.coroutines.flow.Flow

interface ListOrdersFulfilledRepository {

    fun getListOrdersFulfilled(idOrder: String): Flow<NetworkResult<List<ListOrdersModel>>>

}