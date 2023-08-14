package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    fun getDesh(nombrecategoria:String,moneda:String): Flow<NetworkResult<List<DishModel>>>
}