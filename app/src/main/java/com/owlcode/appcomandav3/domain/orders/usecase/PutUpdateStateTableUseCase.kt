package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.zones.repository.UpdateStateTableRepository
import javax.inject.Inject

class PutUpdateStateTableUseCase @Inject constructor(
    private val updateStateTableRepository : UpdateStateTableRepository
){
    suspend operator fun invoke(idZona: String,idMesa: Int,estadoMesa: String,nameMozo: String): NetworkResult<Void> {
        return updateStateTableRepository.putUpdateStateTable(idZona,idMesa,estadoMesa,nameMozo)
    }
}