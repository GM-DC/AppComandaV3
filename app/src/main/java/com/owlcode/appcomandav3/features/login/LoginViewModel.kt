package com.owlcode.appcomandav3.features.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owlcode.appcomandav3.common.utils.Companion.DATA_USER
import com.owlcode.appcomandav3.common.utils.Companion.NOMBRE_USURIO
import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.passcode.model.LoginUserModel
import com.owlcode.appcomandav3.domain.passcode.usecase.PostLoginUserUseCase
import com.owlcode.appcomandav3.domain.users.usecase.GetUsuarioUseCase
import com.owlcode.appcomandav3.features.login.model.StateLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUsuarioUseCase : GetUsuarioUseCase,
    private val postLoginUserUseCase : PostLoginUserUseCase
) : ViewModel() {

    private val _state = mutableStateOf(StateLogin())
    val state: State<StateLogin> = _state

    private val _uiEvnet = MutableSharedFlow<UIEvent>()
    val uiEvnet = _uiEvnet.asSharedFlow()

    init {
        onEvent(LoginEvent.InitUser)
    }


    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.InitUser -> {
                viewModelScope.launch {
                    getUsuarioUseCase().onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    listUsers = result.data ?: listOf(),
                                    isLoading = false
                                )
                            }
                            is NetworkResult.Error -> {
                                _state.value = state.value.copy(
                                    message = result.message,
                                    isLoading = false
                                )
                            }
                            is NetworkResult.Loading -> {
                                _state.value = state.value.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }.launchIn(this)
                }
            }
            is LoginEvent.InputPassword -> {
                _state.value = state.value.copy(
                    password = event.text
                )
            }
            is LoginEvent.ValidatePassword -> {
                viewModelScope.launch {
                    val loginRequest = LoginUserModel(usuariomozo = state.value.user,passmozo = state.value.password)
                    postLoginUserUseCase(loginRequest).also { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    responseLogin = result.data,
                                    isLoading = false
                                )
                                result.data?.let { DATA_USER = it }
                                NOMBRE_USURIO = state.value.responseLogin?.usuario.orEmpty()
                                if (state.value.responseLogin != null){
                                    viewModelScope.launch(Dispatchers.Main) {
                                        _uiEvnet.emit(UIEvent.GoToZones)
                                    }
                                }
                            }
                            is NetworkResult.Error -> {
                                _state.value = state.value.copy(
                                    message = result.message,
                                    isLoading = false
                                )
                            }
                            is NetworkResult.Loading -> {
                                _state.value = state.value.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
            }
            is LoginEvent.InputUser -> {
                _state.value = state.value.copy(
                    user = event.text
                )
            }
        }
    }
}