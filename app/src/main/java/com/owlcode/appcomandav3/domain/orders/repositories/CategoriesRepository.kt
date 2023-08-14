package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    fun getCategories(): Flow<NetworkResult<List<CategoryModel>>>
}