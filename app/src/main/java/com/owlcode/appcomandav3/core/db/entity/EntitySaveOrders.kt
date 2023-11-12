package com.owlcode.appcomandav3.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntitySaveOrders(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo var zona: Int,
    @ColumnInfo val mesa: String

)
/*
@Entity(foreignKeys = [ForeignKey(
    entity = EntitySaveOrders::class,
    parentColumns = ["id"],
    childColumns = ["orderId"],
    onDelete = ForeignKey.CASCADE
)])
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val pedidoId: Int,
    @ColumnInfo var cantidad: Int,
    @ColumnInfo val namePlato: String,
    @ColumnInfo val categoria: String,
    @ColumnInfo val precio: Double,
    @ColumnInfo var precioTotal: Double,
    @ColumnInfo var observacion: String,
    @ColumnInfo var estadoPedido: String,
    @ColumnInfo var idProducto: Int,
    @ColumnInfo var camanda: String,
    @ColumnInfo val igv: Double,
    @ColumnInfo val psigv: Double,
    @ColumnInfo val flag_color: Int,
    @ColumnInfo val orderId: Int
)*/






