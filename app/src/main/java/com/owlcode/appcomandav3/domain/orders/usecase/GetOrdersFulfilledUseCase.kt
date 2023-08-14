package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.domain.orders.repositories.ListOrdersFulfilledRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersFulfilledUseCase @Inject constructor(
    private val listOrdersFulfilledRepository : ListOrdersFulfilledRepository
) {

    operator fun invoke(idPedido:String): Flow<NetworkResult<List<ListOrdersModel>>> {
        return listOrdersFulfilledRepository.getListOrdersFulfilled(idPedido)
    }

}