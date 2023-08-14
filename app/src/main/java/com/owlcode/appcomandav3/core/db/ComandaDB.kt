package com.owlcode.appcomandav3.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.owlcode.appcomandav3.core.db.dao.DaoCategoria
import com.owlcode.appcomandav3.core.db.dao.DaoLoginExito
import com.owlcode.appcomandav3.core.db.dao.DaoUsuario
import com.owlcode.appcomandav3.core.db.dao.DaoZona
import com.owlcode.appcomandav3.core.db.entity.EntityCategoria
import com.owlcode.appcomandav3.core.db.entity.EntityLoginExito
import com.owlcode.appcomandav3.core.db.entity.EntitySaveOrders
import com.owlcode.appcomandav3.core.db.entity.EntityUsuario
import com.owlcode.appcomandav3.core.db.entity.EntityZona
import com.owlcode.appcomandav3.core.db.dao.DaoSaveOrders

@Database(
    entities = [EntityZona::class,
                EntityCategoria::class,
                EntityUsuario::class,
                EntityLoginExito::class,
                EntitySaveOrders::class],
                version = 2)
    abstract class ComandaDB : RoomDatabase() {
    abstract fun daoZona(): DaoZona
    abstract fun daoCategoria(): DaoCategoria
    abstract fun daoUsuario(): DaoUsuario
    abstract fun daoLoginExito(): DaoLoginExito
    abstract fun daoSaveOrders(): DaoSaveOrders
}