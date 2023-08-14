package com.owlcode.appcomandav3.data.orders.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.orders.remote.DishApi
import com.owlcode.appcomandav3.data.orders.remote.dto.toDishModel
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.domain.orders.repositories.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DishRepositoryImpl @Inject constructor(
    private val api: DishApi
): DishRepository {
    override fun getDesh(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listTable: MutableList<DishModel> = mutableListOf()
            api.getDishes(nombrecategoria,"0001").forEach { listTable.add(it.toDishModel())  }
            emit(NetworkResult.Success( listTable ))
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