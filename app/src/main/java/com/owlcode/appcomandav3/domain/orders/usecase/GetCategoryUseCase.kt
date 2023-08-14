package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.domain.orders.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
    private val categoryRepository : CategoriesRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<CategoryModel>>> {
        return categoryRepository.getCategories()
    }

}