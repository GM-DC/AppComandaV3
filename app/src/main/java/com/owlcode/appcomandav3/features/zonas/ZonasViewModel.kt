package com.owlcode.appcomandav3.features.zonas

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owlcode.appcomandav3.common.utils
import com.owlcode.appcomandav3.common.utils.Companion.DATA_ZONA
import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.zones.usecase.GetTableUseCase
import com.owlcode.appcomandav3.domain.zones.usecase.GetZonesUseCase
import com.owlcode.appcomandav3.features.zonas.model.StateZona
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ZonasViewModel @Inject constructor(
    private val getZonesUseCase : GetZonesUseCase,
    private val getTableUseCase : GetTableUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(StateZona())
    val state: State<StateZona> = _state

    private val _uiEvnet = MutableSharedFlow<UIEvent>()
    val uiEvnet = _uiEvnet.asSharedFlow()

    lateinit var job: Job

    init {
        onEvent(ZonasEvent.InitZona)
    }

    fun onEvent(event : ZonasEvent){
        when(event){
            is ZonasEvent.InitZona -> {
                viewModelScope.launch {
                    getZonesUseCase().onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    listaZona = result.data ?: listOf(),
                                    isLoading = false
                                )
                                DATA_ZONA = state.value.listaZona[0]
                                onEvent(ZonasEvent.InitMesa(state.value.listaZona[0].idZona))
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
            is ZonasEvent.InitMesa -> {
                job = viewModelScope.launch {
                    getTableUseCase("piso eq '${event.idMesa}' and tipo eq 'A'").onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    listaMesa = result.data?.toMutableList() ?: mutableListOf(),
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
                                    isLoading = false
                                )
                            }
                        }
                    }.launchIn(this)
                }
            }
            is ZonasEvent.SelectMesa -> {

            }
            is ZonasEvent.SelectZona -> {

            }
            is ZonasEvent.CancelarCorrutine -> {
                if (::job.isInitialized){
                    job.cancel()
                }
            }
        }
    }

}