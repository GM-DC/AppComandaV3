package com.owlcode.appcomandav3.domain.zones.repository

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.zones.model.ZoneModel
import kotlinx.coroutines.flow.Flow

interface ZonesRepository {
    fun getZones(): Flow<NetworkResult<List<ZoneModel>>>
}