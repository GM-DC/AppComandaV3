package com.owlcode.appcomandav3.domain.passcode.usecase

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserModel
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserResponseModel
import com.owlcode.appcomandav3.domain.passcode.repository.LoginUserRepository
import javax.inject.Inject

class PostLoginUserUseCase @Inject constructor(
    private val loginUserRepository : LoginUserRepository
) {
    suspend operator fun invoke(login: LoginUserModel): NetworkResult<LoginUserResponseModel> {
        return loginUserRepository.postLoginUser(login)
    }
}