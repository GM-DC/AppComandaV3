package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.PreCount
import kotlinx.coroutines.flow.Flow

interface PrintPreCountRepository {

    fun getOrder(idPedido:String): Flow<NetworkResult<PreCount>>

}