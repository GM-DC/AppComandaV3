package com.owlcode.appcomandav3.features.orders

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owlcode.appcomandav3.common.PrintOrder
import com.owlcode.appcomandav3.common.PrintPreCount
import com.owlcode.appcomandav3.common.datamodelconst.DataListSave
import com.owlcode.appcomandav3.common.utils
import com.owlcode.appcomandav3.common.utils.Companion.DATA_LISTA_GUARDADA
import com.owlcode.appcomandav3.common.utils.Companion.DATA_TABLE
import com.owlcode.appcomandav3.common.utils.Companion.DATA_USER
import com.owlcode.appcomandav3.common.utils.Companion.DATA_ZONA
import com.owlcode.appcomandav3.common.utils.Companion.IMP_PRECUENTA
import com.owlcode.appcomandav3.common.utils.Companion.NOMBRE_MOZO
import com.owlcode.appcomandav3.common.utils.Companion.NOMBRE_USURIO
import com.owlcode.appcomandav3.core.NetworkResult
import com.owlcode.appcomandav3.domain.orders.model.DetalleModel
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.domain.orders.model.OrderDetailModel
import com.owlcode.appcomandav3.domain.orders.model.OrderModel
import com.owlcode.appcomandav3.domain.orders.model.SendOrdersModel
import com.owlcode.appcomandav3.domain.orders.usecase.GetCategoryUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetDishesUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetOrderUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetOrdersFulfilledUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.GetPreCountUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.PostSendOrdersUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.PutUpdateColorOrderUseCase
import com.owlcode.appcomandav3.domain.orders.usecase.PutUpdateStateTableUseCase
import com.owlcode.appcomandav3.features.orders.model.StateOrden
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val putUpdateColorOrderUseCase : PutUpdateColorOrderUseCase,
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
        _state.value = state.value.copy(
            datoMesa = DATA_TABLE,
            datoZone = DATA_ZONA
        )
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
                                println("NetworkResult.Success: PASO AQUI 1")
                                val listServicio = result.data?.toMutableList() ?: mutableListOf()
                                var listGuardada = listOf<ListOrdersModel>()
                                DATA_LISTA_GUARDADA.filter {
                                    it.idMesa == state.value.datoMesa?.idMesa &&
                                            it.idZona == state.value.datoZone?.idZona
                                }.map {
                                    listGuardada = it.listPedido
                                }
                                listServicio.addAll(listGuardada)
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    listPedido = listServicio
                                )
                            }
                            is NetworkResult.Error -> {
                                val listServicio : MutableList<ListOrdersModel> = mutableListOf()
                                var listGuardada = listOf<ListOrdersModel>()
                                DATA_LISTA_GUARDADA.filter {
                                    it.idMesa == state.value.datoMesa?.idMesa &&
                                            it.idZona == state.value.datoZone?.idZona
                                }.map {
                                    listGuardada = it.listPedido
                                }
                                listServicio.addAll(listGuardada)
                                _state.value = state.value.copy(
                                    isLoading = false,
                                    listPedido = listServicio
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
            is OrdenComandaEvent.InitDataObservacion -> {
                val listActual = state.value.listPedido.toMutableList()
                _state.value = state.value.copy(
                    inputText = listActual[event.position].observacion
                )
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
                    if(state.value.listPedido.none{it.estadoPedido == "PENDIENTE"}){
                        onEvent(OrdenComandaEvent.ActualizarEstadoMesa(
                            state.value.datoZone?.idZona.orEmpty(),
                            state.value.datoMesa?.idMesa ?: -1,
                            "O",
                            DATA_USER.usuario
                        ))
                    }
                    addPedido()
                }else{
                    Toast.makeText(getContexto, "Mesa ocupada por $NOMBRE_MOZO", Toast.LENGTH_SHORT).show()
                }
                viewModelScope.launch(Dispatchers.Main) {
                    _uiEvnet.emit(UIEvent.GoToScrolll(state.value.listPedido.size))
                }
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
                val listActual = state.value.listPedido.toMutableList()
                listActual.removeAt(event.position)
                _state.value = state.value.copy(
                    listPedido = listActual
                )
                if(state.value.listPedido.size == 0){
                    onEvent(OrdenComandaEvent.ActualizarEstadoMesa(
                        state.value.datoZone?.idZona.orEmpty(),
                        state.value.datoMesa?.idMesa ?: -1,
                        "L",
                        DATA_USER.usuario
                    ))
                }
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
                state.value.listPreCuenta?.let {
                    val statoPrint = PrintPreCount().printTcp(IMP_PRECUENTA, 9100, it, getContexto)
                    if (statoPrint){
                        Toast.makeText(getContexto, "Se envio exitosamente", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(getContexto, "Surgio algun problema", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Toast.makeText(getContexto, "Pre Cuenta vacia", Toast.LENGTH_SHORT).show()
                }
            }
            is OrdenComandaEvent.OnClickEnviarComanda -> {
                if (state.value.listPedido.count{it.estadoPedido=="PENDIENTE"}>0){
                    onEvent(OrdenComandaEvent.OnEnviarComanda(listSendOrders()))
                }else{
                    Toast.makeText(getContexto, "Ingrese pedidos nuevos", Toast.LENGTH_SHORT).show()
                }
            }
            is OrdenComandaEvent.OnEnviarComanda -> {
                viewModelScope.launch {
                    postSendOrdersUseCase(event.pedido).also { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                onEvent(OrdenComandaEvent.RespuestaDelEnvioComanda(result.data?.iD_PEDIDO.toString(),result.data))
                                onEvent(OrdenComandaEvent.ActualizarEstadoMesa(DATA_TABLE.idZona,DATA_TABLE.idMesa,"O",DATA_USER.usuario))
                            }
                            is NetworkResult.Error -> {}
                            is NetworkResult.Loading -> {}
                        }
                    }
                }
            }
            is OrdenComandaEvent.RespuestaDelEnvioComanda -> {
                viewModelScope.launch {
                    getOrderUseCase(event.idPedido).onEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                onEvent(OrdenComandaEvent.FiltarListaParaImprimirComanda(result.data!!,event.resultComandaEnviarda!!))
                            }
                            is NetworkResult.Error -> {}
                            is NetworkResult.Loading -> {}
                        }
                    }.launchIn(this)
                }
            }
            is OrdenComandaEvent.FiltarListaParaImprimirComanda -> {
                val listaCodComanda: ArrayList<String> = ArrayList()
                val idPedido = event.resultSendOrder.iD_PEDIDO.toString()

                event.resultSendOrder.detalle.forEach {
                    listaCodComanda.add(it.comanda)
                }
                val listaDetalleComanda = ArrayList<OrderDetailModel>()
                val listaComanda = ArrayList<OrderModel>()
                val impComanda = PrintOrder()
                event.resp.forEach { cabezera ->
                    cabezera.detalle.forEach { detalleComanda ->
                        for (i in state.value.listPedido.indices){
                            if(detalleComanda.producto == state.value.listPedido[i].namePlato && state.value.listPedido[i].estadoPedido == "PENDIENTE"){
                                listaDetalleComanda.add(
                                    OrderDetailModel(
                                        detalleComanda.iD_PRODUCTO,
                                        detalleComanda.producto,
                                        state.value.listPedido[i].cantidad,
                                        detalleComanda.precio,
                                        detalleComanda.precio*state.value.listPedido[i].cantidad,
                                        state.value.listPedido[i].observacion,
                                        detalleComanda.noM_IMP,
                                        secuencia = i)
                                )
                            }
                        }
                    }
                    listaComanda.add(OrderModel(cabezera.numerO_PEDIDO,cabezera.destino,cabezera.zona,cabezera.mesa,cabezera.mesero,cabezera.rutacomanda,cabezera.fechayhora,listaDetalleComanda))
                }
                var init = " "
                listaCodComanda.forEach {
                    if (init != it){
                        onEvent(OrdenComandaEvent.PutUpdateColorOrder(it,idPedido.toInt()))
                        init = it
                    }
                }
                listaComanda.forEach {
                    impComanda.printTcp(it.rutacomanda,9100,it)
                }
            }
            is OrdenComandaEvent.PutUpdateColorOrder -> {
                viewModelScope.launch {
                    putUpdateColorOrderUseCase(event.comanda,event.idpedido)
                    _uiEvnet.emit(UIEvent.GoToZona)
                }
            }
            is OrdenComandaEvent.ActualizarEstadoMesa -> {
                viewModelScope.launch {
                    putUpdateStateTableUseCase(event.idZona,event.idMesa,event.estadoMesa,event.nameMozo)
                }
            }
            is OrdenComandaEvent.GuardarListaPedidoPendiente -> {
                val listPedidoPendiente = state.value.listPedido.filter {
                    it.estadoPedido == "PENDIENTE"
                }
                val index = DATA_LISTA_GUARDADA.indexOfFirst { dataListSave ->
                    DATA_ZONA.idZona == dataListSave.idZona && DATA_TABLE.idMesa == dataListSave.idMesa
                }
                println("listPedidoPendiente: $listPedidoPendiente")
                println("index: $index")
                if(index == -1){
                    DATA_LISTA_GUARDADA.add(
                        DataListSave(
                            idZona = DATA_ZONA.idZona,
                            idMesa = DATA_TABLE.idMesa,
                            listPedido = listPedidoPendiente.toMutableList()
                        )
                    )
                }else{
                    DATA_LISTA_GUARDADA[index] = DataListSave(
                        idZona = DATA_ZONA.idZona,
                        idMesa = DATA_TABLE.idMesa,
                        listPedido = listPedidoPendiente.toMutableList()
                    )
                }
                println("DATA_LISTA_GUARDADA: $DATA_LISTA_GUARDADA")
                viewModelScope.launch(Dispatchers.Main){
                    _uiEvnet.emit(UIEvent.GoToBack)
                }
            }
        }
    }


    private fun listSendOrders() : SendOrdersModel {
        val datoLoginExitoso = DATA_USER
        val idTable = DATA_TABLE.idMesa.toString()
        val idZona = DATA_TABLE.idZona

        var importeTotalLista = 0.0
        var TotalIgv= 0.0
        var subTotalLista= 0.0

        fun listDetailOrder() : MutableList<DetalleModel> {
            val listDetailOrder : MutableList<DetalleModel> = mutableListOf()
            val listOrdersFilter : MutableList<ListOrdersModel> = mutableListOf()
            var secuencia = 0

            listOrdersFilter.clear()
            for(i in state.value.listPedido.indices){
                var lt = state.value.listPedido[i]
                var conteo = 0
                var pos = 0
                for (e in listOrdersFilter.indices){
                    if (state.value.listPedido[i].namePlato == listOrdersFilter[e].namePlato){
                        conteo += 1
                    }
                    if (conteo == 1){
                        pos = e
                        break
                    }
                }

                if (conteo == 0){
                    listOrdersFilter.add(state.value.listPedido[i])
                }else{
                    listOrdersFilter[pos] = ListOrdersModel(
                        cantidad = lt.cantidad+listOrdersFilter[pos].cantidad,
                        namePlato = lt.namePlato,
                        categoria = lt.categoria,
                        precio = lt.precio,
                        precioTotal =  ((lt.cantidad+listOrdersFilter[pos].cantidad)*lt.precio),
                        observacion = lt.observacion,
                        estadoPedido =lt.estadoPedido,
                        idProducto = lt.idProducto,
                        camanda = lt.camanda,
                        igv = utils().priceIGV(((lt.cantidad+listOrdersFilter[pos].cantidad)*lt.precio)),
                        psigv = utils().priceSubTotal(((lt.cantidad+listOrdersFilter[pos].cantidad)*lt.precio)),
                        flag_color = lt.flag_color)
                }
            }
            importeTotalLista = listOrdersFilter.sumOf { t -> t.precioTotal }
            TotalIgv = listOrdersFilter.sumOf { t -> t.igv }
            subTotalLista= listOrdersFilter.sumOf { t -> t.psigv}

            listOrdersFilter.forEach {
                listDetailOrder.add(
                    DetalleModel(
                        iD_PEDIDO= 0,
                        iD_PRODUCTO= it.idProducto,
                        cantidad= it.cantidad,
                        nombre= it.namePlato,
                        precio= it.precio,
                        descuento=0,
                        igv= it.igv,
                        importe= it.precioTotal,
                        canT_DESPACHADA=0,
                        canT_FACTURADA=0,
                        observacion= it.observacion,
                        secuencia= secuencia,
                        preciO_ORIGINAL= it.precio,
                        tipo="1",
                        importE_DSCTO=0,
                        afectO_IGV="1",
                        comision=0,
                        iD_PRESUPUESTO=0,
                        cdG_SERV="",
                        flaG_C="0",
                        flaG_P="",
                        flaG_COLOR= "0",
                        noM_UNIDAD="",
                        comanda= it.camanda,
                        mozo= datoLoginExitoso.nombreUsuario,
                        unidad="0001",      //***
                        codigO_BARRA="",
                        poR_PERCEPCION=0,
                        percepcion=0,
                        valoR_VEN= it.psigv,
                        uniD_VEN="",
                        fechA_VEN= utils().getFecha(),
                        factoR_CONVERSION=1, //**
                        cdG_KIT="",
                        swT_PIGV="S",      //**
                        swT_PROM="N",
                        canT_KIT=0,
                        swT_DCOM="N",     //**
                        swT_SABOR="0",
                        swT_FREE="N",      //**
                        noM_IMP="",
                        seC_PROD=0,
                        poR_DETRACCION=0,
                        detraccion=0,
                        usuariO_ANULA="",
                        fechA_ANULA= utils().getFecha(),
                        margen=0,
                        importE_MARGEN=0,
                        costO_ADIC=0)
                )
                secuencia += 1
            }
            return listDetailOrder
        }
        fun headOrder() : SendOrdersModel {
            val sendOrders = SendOrdersModel(
                iD_PEDIDO=0,
                numerO_PEDIDO="",
                noM_MON="",
                smB_MON="",
                conD_PAGO=datoLoginExitoso!!.cdgpago ?: "",
                persona="",
                ruc="",
                freC_DIAS="",
                codigO_VENDEDOR= datoLoginExitoso.cdG_VENDEDOR ,
                codigO_CPAGO= datoLoginExitoso.cdgpago ,
                codigO_MONEDA= datoLoginExitoso.cdgmoneda,
                fechA_PEDIDO=utils().getFecha(),
                numerO_OCLIENTE="",
                importE_STOT=subTotalLista,
                importE_IGV=TotalIgv,
                importE_DESCUENTO=0,
                importE_TOTAL=importeTotalLista,
                porcentajE_DESCUENTO=0,
                porcentajE_IGV= datoLoginExitoso.poR_IGV.toInt(),
                observacion = "",
                serie="",
                estado="0001",
                iD_CLIENTE=datoLoginExitoso.iD_CLIENTE,
                importE_ISC=0,
                usuariO_CREACION=datoLoginExitoso.usuariocreacion,
                usuariO_AUTORIZA=datoLoginExitoso.usuarioautoriza,
                fechA_CREACION=utils().getFecha(),
                fechA_MODIFICACION=utils().getFecha(),
                codigO_EMPRESA=datoLoginExitoso.codigO_EMPRESA,
                codigO_SUCURSAL=datoLoginExitoso.sucursal,
                valoR_VENTA=subTotalLista,
                iD_CLIENTE_FACTURA=datoLoginExitoso.iD_CLIENTE,
                codigO_VENDEDOR_ASIGNADO="",
                fechA_PROGRAMADA=utils().getFecha(),
                facturA_ADELANTADA="",
                contacto="",
                emaiL_CONTACTO="",
                lugaR_ENTREGA="",
                iD_COTIZACION=datoLoginExitoso.iD_COTIZACION.toInt(),
                comision=0,
                puntO_VENTA="",
                redondeo=datoLoginExitoso.redondeo,
                validez="",
                motivo="",
                correlativo="",
                centrO_COSTO="",
                tipO_CAMBIO=0,
                sucursal=datoLoginExitoso.sucursal,
                mesa= idTable,
                piso= idZona,
                detalle= listDetailOrder(),
            )
            return sendOrders
        }
        return headOrder()
    }




}