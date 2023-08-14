package com.owlcode.appcomandav3.domain.zones.repository

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import kotlinx.coroutines.flow.Flow

interface TablesRepository {

    fun getTables(filter:String): Flow<NetworkResult<List<TableModel>>>

}