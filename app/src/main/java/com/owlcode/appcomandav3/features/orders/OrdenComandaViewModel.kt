package com.owlcode.appcomandav3.features.orders

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owlcode.appcomandav3.common.utils
import com.owlcode.appcomandav3.common.utils.Companion.DATA_TABLE
import com.owlcode.appcomandav3.common.utils.Companion.NOMBRE_MOZO
import com.owlcode.appcomandav3.common.utils.Companion.NOMBRE_USURIO
import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.domain.orders.usecase.GetCategoryUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetDishesUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetOrderUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetOrdersFulfilledUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetPreCountUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.PostSendOrdersUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.PutUpdateStateTableUseCase
import com.owlcode.appcomandav3.features.login.UIEvent
import com.owlcode.appcomandav3.features.orders.model.StateOrden
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrdenComandaViewModel @Inject constructor(
    @ApplicationContext appContext: Context,
    private val getCategoryUseCase : GetCategoryUseCase,
    private val getDishesUseCase : GetDishesUseCase,
    private val putUpdateStateTableUseCase : PutUpdateStateTableUseCase,
    private val getPreCountUseCase : GetPreCountUseCase,
    private val getOrderUseCase : GetOrderUseCase,
    private val getOrdersFulfilledUseCase : GetOrdersFulfilledUseCase,
    private val postSendOrdersUseCase : PostSendOrdersUseCase,
) : ViewModel() {
    private val getContexto = appContext

    private val _state = mutableStateOf(StateOrden())
    val state: State<StateOrden> = _state

    private val _uiEvnet = MutableSharedFlow<UIEvent>()
    val uiEvnet = _uiEvnet.asSharedFlow()

    init {
        onEvent(OrdenComandaEvent.InitCategoria)
        onEvent(OrdenComandaEvent.InitPedidoDespachado(DATA_TABLE.idPedido.toString()))
        onEvent(OrdenComandaEvent.InitPreCuenta(DATA_TABLE.idPedido.toString()))
    }

    fun onEvent(event: OrdenComandaEvent){
        when(event){
            is OrdenComandaEvent.InitCategoria -> {
                viewModelScope.launch {
                    getCategoryUseCase().onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    listCategoria = result.data?.toMutableList() ?: listOf(),
                                    isLoading = false
                                )
                                onEvent(OrdenComandaEvent.InitPlatos(state.value.listCategoria[0].nameCategoria))
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
            is OrdenComandaEvent.InitPlatos -> {
                viewModelScope.launch {
                    getDishesUseCase(nombrecategoria = event.nameCategoria, moneda = "0001").onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    listPlatos = result.data!!.toMutableList(),
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
            is OrdenComandaEvent.InitPedidoDespachado -> {
                viewModelScope.launch {
                    getOrdersFulfilledUseCase(event.idPedido).onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    listPedido = result.data?.toMutableList() ?: mutableListOf()
                                )
                            }
                            is NetworkResult.Error -> {
                                _state.value = state.value.copy(
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
            is OrdenComandaEvent.InitPreCuenta -> {
                viewModelScope.launch {
                    getPreCountUseCase(event.idPedido).onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                _state.value = state.value.copy(
                                    listPreCuenta = result.data,
                                    isLoading = false
                                )
                            }
                            is NetworkResult.Error -> {
                                _state.value = state.value.copy(
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
            is OrdenComandaEvent.OnClickAddProducto -> {
                fun addPedido(){
                    onEvent(OrdenComandaEvent.BuscarCoincidencia(event.platoAgregado.iD_PRODUCTO))
                    val action = state.value.coincidencia[0]
                    val pos = state.value.coincidencia[1]
                    val listPedidoActual = state.value.listPedido.toMutableList()
                    if (action == 0) {
                        listPedidoActual.add(
                            ListOrdersModel(
                                cantidad= 1,
                                namePlato= event.platoAgregado.nombre,
                                categoria= event.platoAgregado.codigo,
                                precio= event.platoAgregado.preciO_VENTA,
                                precioTotal= event.platoAgregado.preciO_VENTA,
                                observacion= "",
                                estadoPedido= "PENDIENTE",
                                idProducto= event.platoAgregado.iD_PRODUCTO,
                                camanda= event.platoAgregado.comanda,
                                igv= utils().priceIGV(event.platoAgregado.preciO_VENTA),
                                psigv= utils().priceSubTotal(event.platoAgregado.preciO_VENTA),
                                flag_color= 0,
                            )
                        )
                    }else {
                        val cantidad = listPedidoActual[pos].cantidad + 1
                        val precioTotal = listPedidoActual[pos].precio * cantidad
                        listPedidoActual[pos] =
                            ListOrdersModel(
                                cantidad= cantidad,
                                namePlato= listPedidoActual[pos].namePlato,
                                categoria= listPedidoActual[pos].categoria,
                                precio= listPedidoActual[pos].precio,
                                precioTotal= precioTotal,
                                observacion= listPedidoActual[pos].observacion,
                                estadoPedido= "PENDIENTE",
                                idProducto= listPedidoActual[pos].idProducto,
                                camanda= listPedidoActual[pos].camanda,
                                igv= utils().priceIGV(listPedidoActual[pos].precio),
                                psigv= utils().priceSubTotal(listPedidoActual[pos].precio),
                                flag_color= 0,
                            )
                    }
                    _state.value = state.value.copy(
                        listPedido = listPedidoActual
                    )
                }
                if (NOMBRE_MOZO == NOMBRE_USURIO || NOMBRE_MOZO.isBlank()){
                    addPedido()
                }else{
                    Toast.makeText(getContexto, "Mesa ocupada por $NOMBRE_MOZO", Toast.LENGTH_SHORT).show()
                }
            }
            is OrdenComandaEvent.InitDataObservacion -> {
                val listActual = state.value.listPedido.toMutableList()
                _state.value = state.value.copy(
                    inputText = listActual[event.position].observacion
                )
            }
            is OrdenComandaEvent.OnClickAgregarNotaProducto -> {
                val listActual = state.value.listPedido.toMutableList()
                listActual[event.position].observacion = event.textObservacion
                _state.value = state.value.copy(
                    listPedido = listActual,
                    inputText = event.textObservacion
                )
            }
            is OrdenComandaEvent.OnClickAumentarProducto -> {
                val listActual = state.value.listPedido.toMutableList()
                val cantidad = listActual[event.position].cantidad + 1
                val precioTotal = listActual[event.position].precio * cantidad.toDouble()
                listActual[event.position] =
                    ListOrdersModel(
                        cantidad= cantidad,
                        namePlato= listActual[event.position].namePlato,
                        categoria= listActual[event.position].categoria,
                        precio= listActual[event.position].precio,
                        precioTotal= precioTotal,
                        observacion= listActual[event.position].observacion,
                        estadoPedido= listActual[event.position].estadoPedido,
                        idProducto= listActual[event.position].idProducto,
                        camanda= listActual[event.position].camanda,
                        igv= utils().priceIGV(listActual[event.position].precio),
                        psigv= utils().priceSubTotal(listActual[event.position].precio),
                        flag_color= 0,
                    )

                _state.value = state.value.copy(
                    listPedido = listActual
                )
            }
            is OrdenComandaEvent.OnClickDisminuirProducto -> {
                val listActual = state.value.listPedido.toMutableList()
                if (listActual[event.position].cantidad > 1){
                    val cantidad = listActual[event.position].cantidad - 1
                    val precioTotal = listActual[event.position].precio * cantidad.toDouble()
                    listActual[event.position] =
                        ListOrdersModel(
                            cantidad= cantidad,
                            namePlato= listActual[event.position].namePlato,
                            categoria= listActual[event.position].categoria,
                            precio= listActual[event.position].precio,
                            precioTotal= precioTotal,
                            observacion= listActual[event.position].observacion,
                            estadoPedido= listActual[event.position].estadoPedido,
                            idProducto= listActual[event.position].idProducto,
                            camanda= listActual[event.position].camanda,
                            igv= utils().priceIGV(listActual[event.position].precio),
                            psigv= utils().priceSubTotal(listActual[event.position].precio),
                            flag_color= 0,
                        )
                    _state.value = state.value.copy(
                        listPedido = listActual
                    )
                }
            }
            is OrdenComandaEvent.OnSwipeDelete -> {
                Log.d("Gian", "---> ItemPosition: ${event.position}")
                val listActual = state.value.listPedido.toMutableList()
                listActual.removeAt(event.position)
                _state.value = state.value.copy(
                    listPedido = listActual
                )
            }
            is OrdenComandaEvent.BuscarCoincidencia -> {
                var action = 0
                var pos = -1

                for (i in state.value.listPedido.indices) {
                    if (state.value.listPedido[i].idProducto == event.idProducto && state.value.listPedido[i].estadoPedido=="PENDIENTE") {
                        action += 1
                    }
                    if (action == 1) {
                        pos = i
                        println("posicion: $pos")
                        break
                    }
                }
                _state.value = state.value.copy(
                    coincidencia = listOf(action,pos)
                )
            }

            is OrdenComandaEvent.OnClickLimpiar -> {
                var size = state.value.listPedido.size
                var cont = 0
                val listActual = state.value.listPedido.toMutableList()
                if (listActual.size>0){
                    do{
                        if (listActual[cont].estadoPedido=="PENDIENTE"){
                            listActual.removeAt(cont)
                            cont=0
                            size-=1
                        }else{
                            cont+=1
                        }
                    }while(size != cont)
                }
                _state.value = state.value.copy(
                    listPedido = listActual
                )
            }
            is OrdenComandaEvent.OnClickPreCuenta -> {

            }

        }
    }
}