package com.owlcode.appcomandav3.core.db.dao

import androidx.room.Dao

@Dao
interface DaoSaveOrders {
    /*
        @Transaction
        @Query("SELECT * FROM EntitySaveOrders WHERE (zona = :zone) AND (mesa LIKE :table)")
        fun getOrdersWithPedidosByZoneTable(zone: Int, table: String): Flow<List<OrdersWithPedidos>>

        @Query("""DELETE FROM Pedido WHERE orderId IN ( SELECT id FROM EntitySaveOrders  WHERE (zona = :zone) AND (mesa LIKE :table)  ) """)
        suspend fun deletePedidosByZoneTable(zone: Int, table: String)

        @Query("""SELECT id FROM EntitySaveOrders WHERE (zona = :zone) AND (mesa LIKE :table) """)
        suspend fun getOrderIdByZoneTable(zone: Int, table: String): Int?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertPedido(pedido: Pedido)



        @Insert
        fun insertZona( insertzonas: EntitySaveOrders)

        @Query("DELETE FROM EntitySaveOrders")
        fun deleteSaveOrders()

        @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'EntitySaveOrders'")
        fun clearPrimaryKey()
        */
}