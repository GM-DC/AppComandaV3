package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.PreCount
import com.owlcode.appcomandav3.domain.orders.repositories.PrintPreCountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreCountUseCase @Inject constructor(
    private val printPreCountRepository : PrintPreCountRepository
) {
    operator fun invoke(idpedido:String): Flow<NetworkResult<PreCount>> {
        return printPreCountRepository.getOrder(idpedido)
    }

}