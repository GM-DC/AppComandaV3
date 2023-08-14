package com.owlcode.appcomandav3.data.passcode.resouce

import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.data.passcode.remote.UserLoginApi
import com.owlcode.appcomandav3.core.db.dao.DaoLoginExito
import com.owlcode.appcomandav3.core.db.entity.EntityLoginExito
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserModel
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserResponseModel
import com.owlcode.appcomandav3.domain.passcode.model.toLoginUserDTO
import com.owlcode.appcomandav3.domain.passcode.repository.LoginUserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(
    private val api: UserLoginApi,
): LoginUserRepository {
    override suspend fun postLoginUser(login : LoginUserModel): NetworkResult<LoginUserResponseModel> {
        val response = try {
            api.postLoginComanda(loginMozo = login.toLoginUserDTO()).toLoginUserResponseModel()
        }catch (e: HttpException) {
            return NetworkResult.Error(
                message = "Huy! Algo salió mal // Code: ${e.code()}",
                data = null
            )
        }catch (e: IOException) {
            return NetworkResult.Error(
                message = "No se pudo llegar al servidor, verifique su conexión a Internet // ${e.message}",
                data = null
            )
        }catch (e: Exception) {
            return NetworkResult.Error(
                message = "Un error desconocido ocurrió",
                data = null
            )
        }
        return NetworkResult.Success(data = response)
    }
}