package com.owlcode.appcomandav3.domain.users.repository

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.users.model.UsuarioDC
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {

     fun getUsuario(): Flow<NetworkResult<List<UsuarioDC>>>
}