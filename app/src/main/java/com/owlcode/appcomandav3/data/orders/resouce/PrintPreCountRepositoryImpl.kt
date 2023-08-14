package com.owlcode.appcomandav3.data.orders.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.orders.remote.PreCountApi
import com.owlcode.appcomandav3.domain.orders.model.PreCount
import com.owlcode.appcomandav3.domain.orders.repositories.PrintPreCountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PrintPreCountRepositoryImpl @Inject constructor(
    private val api: PreCountApi
): PrintPreCountRepository {
    override fun getOrder(idPedido: String): Flow<NetworkResult<PreCount>> = flow{
        emit(NetworkResult.Loading())
        try {
            val dato = api.getPreCuenta(idPedido).toPreCount()
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