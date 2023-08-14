package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.repositories.UpdateColorOrderRespository
import javax.inject.Inject

class PutUpdateColorOrderUseCase @Inject constructor(
    private val updateColorOrderRespository : UpdateColorOrderRespository
){
    suspend operator fun invoke(comanda:String, idpedido:Int): NetworkResult<Void> {
        return updateColorOrderRespository.putUpdateColorOrder(comanda,idpedido)
    }
}