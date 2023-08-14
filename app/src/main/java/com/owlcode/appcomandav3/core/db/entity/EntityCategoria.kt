package com.owlcode.appcomandav3.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityCategoria (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "idCategoria")
    val idCategoria: String,

    @ColumnInfo(name = "nameCategoria")
    val nameCategoria: String,

    @ColumnInfo
    val lis: Int

    )
