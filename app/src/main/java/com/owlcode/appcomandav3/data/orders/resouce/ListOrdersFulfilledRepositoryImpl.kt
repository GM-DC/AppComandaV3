package com.owlcode.appcomandav3.data.orders.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.orders.remote.OrdersFulfilledApi
import com.owlcode.appcomandav3.data.orders.remote.dto.toListOrdersModel
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.domain.orders.repositories.ListOrdersFulfilledRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ListOrdersFulfilledRepositoryImpl  @Inject constructor(

    private val api: OrdersFulfilledApi
): ListOrdersFulfilledRepository {
    override fun getListOrdersFulfilled(idOrder:String): Flow<NetworkResult<List<ListOrdersModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listOrderFufilled: MutableList<ListOrdersModel> = mutableListOf()
            api.getOrdersFulfilled(idOrder).forEach { listOrderFufilled.add(it.toListOrdersModel()) }
            emit(NetworkResult.Success( listOrderFufilled))
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