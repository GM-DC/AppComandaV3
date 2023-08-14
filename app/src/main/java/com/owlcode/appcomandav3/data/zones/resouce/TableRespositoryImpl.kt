package com.owlcode.appcomandav3.data.zones.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.zones.remote.TableApi
import com.owlcode.appcomandav3.data.zones.remote.dto.toTableModel
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.domain.zones.repository.TablesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TableRespositoryImpl @Inject constructor(
    private val api: TableApi
): TablesRepository {
    override fun getTables(filter: String): Flow<NetworkResult<List<TableModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listTable: MutableList<TableModel> = mutableListOf()
            var dato = filter
            do{
                listTable.clear()
                api.getTable(filter).forEach { listTable.add(it.toTableModel())  }
                emit(NetworkResult.Success( listTable ))
                delay(3000)
                if(dato.isEmpty()){
                    dato = filter
                }
            }while(dato == filter)

        } catch (e: HttpException) {
            emit(
                NetworkResult.Error(
                message = "Huy! Algo salió mal",
                data = null
            ))
        } catch (e: IOException) {
            emit(
                NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                data = null
            ))
        }
    }
}