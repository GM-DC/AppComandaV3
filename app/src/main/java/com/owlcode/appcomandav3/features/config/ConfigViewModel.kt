package com.owlcode.appcomandav3.features.config

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owlcode.appcomandav3.common.isValidIP
import com.owlcode.appcomandav3.common.utils.Companion.IMP_PRECUENTA
import com.owlcode.appcomandav3.common.utils.Companion.PORT
import com.owlcode.appcomandav3.common.utils.Companion.URLBASE
import com.owlcode.appcomandav3.domain.config.usecase.GetDataStoreUseCase
import com.owlcode.appcomandav3.domain.config.usecase.SaveDataStoreUseCase
import com.owlcode.appcomandav3.features.config.model.StateConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val getDataStoreUseCase : GetDataStoreUseCase,
    private val saveDataStoreUseCase : SaveDataStoreUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(StateConfig())
    val state: State<StateConfig> = _state

    private val _uiEvnet = MutableSharedFlow<UIEvent>()
    val uiEvnet = _uiEvnet.asSharedFlow()

    init {
        onEvent(ConfigEvent.InitData)
    }

    fun onEvent(event: ConfigEvent){
        when(event){
            is ConfigEvent.InitData -> {
                viewModelScope.launch{
                    getDataStoreUseCase().collect{
                        _state.value = state.value.copy(
                            ip = it.urlBase,
                            puerto = it.port,
                            ipImpresora = it.ipImpresora
                        )
                        URLBASE = state.value.ip
                        PORT = state.value.puerto
                        IMP_PRECUENTA = state.value.ipImpresora
                        if (URLBASE.isNotBlank() && PORT.isNotBlank() && IMP_PRECUENTA.isNotBlank()){
                            println("url: $URLBASE")
                            println("port: $PORT")
                            viewModelScope.launch(Dispatchers.Main) {
                                _uiEvnet.emit(UIEvent.GoToLogin)
                            }
                        }
                    }
                }

            }
            is ConfigEvent.InputIP -> {
                if(isValidIP(event.text)){
                    _state.value = state.value.copy(
                        ip = event.text
                    )
                }
            }
            is ConfigEvent.InputPuerto -> {
                _state.value = state.value.copy(
                    puerto = event.text
                )
            }
            is ConfigEvent.InputIPImpresora -> {
                _state.value = state.value.copy(
                    ipImpresora = event.text
                )
            }
            is ConfigEvent.GuardarDatos -> {
                val urlbase = state.value.ip
                val port = state.value.puerto
                val ipImpresora = state.value.ipImpresora

                if(urlbase.isNotBlank() && urlbase.isNotEmpty() &&
                    port.isNotBlank() && port.isNotEmpty() &&
                    ipImpresora.isNotBlank() && ipImpresora.isNotEmpty()){
                    viewModelScope.launch{
                        URLBASE = urlbase
                        PORT = port
                        IMP_PRECUENTA = ipImpresora
                        saveDataStoreUseCase(urlbase,port,ipImpresora)
                    }
                }
            }
        }
    }
}