package com.owlcode.appcomandav3.data.orders.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.orders.remote.OrderApi
import com.owlcode.appcomandav3.domain.orders.model.OrderModel
import com.owlcode.appcomandav3.domain.orders.repositories.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
): OrderRepository {
    override fun getOrder(idPedido: String): Flow<NetworkResult<List<OrderModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val dato = api.getComanda(idPedido).map { it.toOrderModel() }
            emit(NetworkResult.Success( dato ))
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