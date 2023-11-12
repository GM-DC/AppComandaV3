package com.owlcode.appcomandav3.features.orders

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.owlcode.appcomandav3.R
import com.owlcode.appcomandav3.common.OrdenComandaNav
import com.owlcode.appcomandav3.common.ZoneNav
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.ui.componet.CategoriaItem
import com.owlcode.appcomandav3.ui.componet.PedidoItem
import com.owlcode.appcomandav3.ui.componet.PlatoItem
import com.owlcode.appcomandav3.ui.componet.dialog.DialogConfirmationComanda
import com.owlcode.appcomandav3.ui.componet.dialog.DialogObservaciones
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.AquaSqueeze
import kotlinx.coroutines.flow.collectLatest


@Composable
fun OrdenComandaScreen(
    navController: NavHostController,
    viewModel: OrdenComandaViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var showDialogObservacion by remember { mutableStateOf(false) }
    var showDialogConfirmationComanda by remember { mutableStateOf(false) }
    var positionItem by remember { mutableStateOf(-1) }
    val list = rememberLazyListState()

    LaunchedEffect(Unit){
        viewModel.uiEvnet.collectLatest {
            when(it){
                UIEvent.GoToZona -> {
                    navController.navigate(ZoneNav.ZoneScreen.route){
                        popUpTo(OrdenComandaNav.OrdenComandaScreen.route){
                            inclusive = true
                        }
                    }
                }
                UIEvent.GoToBack -> {
                    navController.navigate(ZoneNav.ZoneScreen.route){
                        popUpTo(OrdenComandaNav.OrdenComandaScreen.route){
                            inclusive = true
                        }
                    }
                }
                is UIEvent.GoToScrolll -> {
                    list.scrollToItem(it.size-1)
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(AquaSqueeze)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                TextPrimary(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    text = "Categoria",
                    txtSize = 18.sp,
                    fontWeight = 600
                )
                LazyHorizontalGrid(
                    modifier = Modifier
                        .padding(5.dp),
                    rows = GridCells.Fixed(2),
                ) {
                    itemsIndexed(state.listCategoria) { index: Int, categoria: CategoryModel ->
                        CategoriaItem(
                            modifier = Modifier,
                            dataCategoria = categoria,
                            onClick = {
                                viewModel.onEvent(OrdenComandaEvent.InitPlatos(categoria.nameCategoria))
                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                TextPrimary(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    text = "Platos",
                    txtSize = 18.sp,
                    fontWeight = 600
                )

                LazyHorizontalGrid(
                    modifier = Modifier
                        .padding(10.dp),
                    rows = GridCells.Fixed(5),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(state.listPlatos) { index: Int, plato: DishModel ->
                        PlatoItem(
                            dataPlato = plato,
                            onClick = {
                                viewModel.onEvent(OrdenComandaEvent.OnClickAddProducto(it))

                            }
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier.weight(9f)
                ) {
                    TextPrimary(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "PEDIDOS",
                        textAlign = TextAlign.Center,
                        txtSize = 22.sp,
                        fontWeight = 600
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .align(Alignment.Start)
                    ) {
                        TextPrimary(text = "Zona: ${state.datoZone?.nombreZonas.orEmpty()}")
                        TextPrimary(text = "Mesa: ${state.datoMesa?.idMesa ?: -1}")
                        TextPrimary(text = "Mozo: ${state.datoMesa?.NombreMozo.orEmpty()}")
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            onClick = {
                                viewModel.onEvent(OrdenComandaEvent.OnClickLimpiar)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clean),
                                contentDescription = "ic_clean"
                            )
                        }
                        IconButton(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            onClick = {
                                viewModel.onEvent(OrdenComandaEvent.OnClickPreCuenta)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_print),
                                contentDescription = "ic_print"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = list
                    ) {
                        itemsIndexed(state.listPedido) { index: Int, item: ListOrdersModel ->
                            PedidoItem(
                                item = item,
                                onClickMas = {
                                    viewModel.onEvent(OrdenComandaEvent.OnClickAumentarProducto(index))
                                },
                                onClickMenos = {
                                    viewModel.onEvent(OrdenComandaEvent.OnClickDisminuirProducto(index))
                                },
                                onClickObservacion = {
                                    showDialogObservacion = true
                                    positionItem = index
                                    viewModel.onEvent(OrdenComandaEvent.InitDataObservacion(index))
                                },
                                onSwipeComplete = {
                                    viewModel.onEvent(OrdenComandaEvent.OnSwipeDelete(index))
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    TextPrimary(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        text = "TOTAL S/. ${state.listPedido.sumOf { it.precioTotal }}",
                        textAlign = TextAlign.End,
                        txtSize = 16.sp,
                        fontWeight = 700
                    )
                    ButtonPrimary(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Enviar",
                        onClick = {
                            showDialogConfirmationComanda = true
                        }
                    )
                }
            }
        }
    }

    DialogObservaciones(
        show = showDialogObservacion,
        text = state.inputText,
        onValueChange = {
            viewModel.onEvent(OrdenComandaEvent.OnClickAgregarNotaProducto(positionItem, it))
        },
        onClickGuardar = {
            showDialogObservacion = false
        },
        onClickCancelar = {
            showDialogObservacion = false
        }
    )


    DialogConfirmationComanda(
        show = showDialogConfirmationComanda,
        onClickConfirma = {
            viewModel.onEvent(OrdenComandaEvent.OnClickEnviarComanda)
        },
        onClickCancelar = {
            showDialogConfirmationComanda = false
        }
    )

    BackHandler {
        viewModel.onEvent(OrdenComandaEvent.GuardarListaPedidoPendiente)
    }

}