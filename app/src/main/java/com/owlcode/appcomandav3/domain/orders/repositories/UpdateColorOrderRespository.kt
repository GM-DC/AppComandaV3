package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult

interface UpdateColorOrderRespository {

    suspend fun putUpdateColorOrder(comanda:String, idpedido:Int): NetworkResult<Void>

}