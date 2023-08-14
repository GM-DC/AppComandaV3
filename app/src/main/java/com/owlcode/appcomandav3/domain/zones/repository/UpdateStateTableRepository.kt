package com.owlcode.appcomandav3.domain.zones.repository

import com.owlcode.appcomandav3.core.NetworkResult

interface UpdateStateTableRepository {

    suspend fun putUpdateStateTable(idZona:String,idMesa:Int,estadoMesa:String,nameMozo:String): NetworkResult<Void>

}