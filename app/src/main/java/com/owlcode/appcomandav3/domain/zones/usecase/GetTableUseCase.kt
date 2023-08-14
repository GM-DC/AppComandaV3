package com.owlcode.appcomandav3.domain.zones.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.domain.zones.repository.TablesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTableUseCase @Inject constructor(
    private val tablesRepository : TablesRepository
) {

    operator fun invoke(filter: String): Flow<NetworkResult<List<TableModel>>> {
        return tablesRepository.getTables(filter)
    }

}