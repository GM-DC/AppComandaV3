package com.owlcode.appcomandav3.domain.orders.repositories

import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel

interface SaveOrdersRepository {

    suspend fun getSaveOrdersByZoneTable(id: Int): ListOrdersModel?

    suspend fun insertSaveOrders(zone:String,table:String, lista: ListOrdersModel)

}