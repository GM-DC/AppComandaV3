package com.owlcode.appcomandav3.domain.orders.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.domain.orders.repositories.DishRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(
    private val dishesRepository : DishRepository
) {

    operator fun invoke(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>> {
        return dishesRepository.getDesh(nombrecategoria,moneda)
    }

}