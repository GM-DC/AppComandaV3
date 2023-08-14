package com.owlcode.appcomandav3.domain.passcode.repository

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.core.db.entity.EntityLoginExito
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserModel
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginUserRepository {

    suspend fun postLoginUser(login: LoginUserModel): NetworkResult<LoginUserResponseModel>

}