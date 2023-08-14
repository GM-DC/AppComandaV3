package com.owlcode.appcomandav3.data.orders.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.orders.remote.CategoryApi
import com.owlcode.appcomandav3.data.orders.remote.dto.toCategoryModel
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.domain.orders.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val api: CategoryApi
) : CategoriesRepository {
    override fun getCategories(): Flow<NetworkResult<List<CategoryModel>>> = flow{
        emit(NetworkResult.Loading())
        try {
            val listCategories: MutableList<CategoryModel> = mutableListOf()
            api.getCategory().forEach { listCategories.add(it.toCategoryModel()) }
            emit(NetworkResult.Success( listCategories ))
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