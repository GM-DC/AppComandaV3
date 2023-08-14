package com.owlcode.appcomandav3.domain.users.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.users.model.UsuarioDC
import com.owlcode.appcomandav3.domain.users.repository.UsuarioRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUsuarioUseCase @Inject constructor(
    private val usuarioRepository : UsuarioRepository
) {

    operator fun invoke(): Flow<NetworkResult<List<UsuarioDC>>> {
        return usuarioRepository.getUsuario()
    }

}