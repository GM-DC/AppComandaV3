package com.owlcode.appcomandav3.domain.zones.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.zones.model.ZoneModel
import com.owlcode.appcomandav3.domain.zones.repository.ZonesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetZonesUseCase @Inject constructor(
    private val zonesRepository : ZonesRepository
){
    operator fun invoke(): Flow<NetworkResult<List<ZoneModel>>> {
        return zonesRepository.getZones()
    }
}