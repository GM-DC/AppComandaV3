package com.owlcode.appcomandav3.data.orders.resouce

import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.domain.orders.repositories.SaveOrdersRepository


class SaveOrdersRepositoryImpl: SaveOrdersRepository {
    override suspend fun getSaveOrdersByZoneTable(id: Int): ListOrdersModel? {
        TODO("Not yet implemented")
    }

    override suspend fun insertSaveOrders(zone: String, table: String, lista: ListOrdersModel) {
        TODO("Not yet implemented")
    }
}