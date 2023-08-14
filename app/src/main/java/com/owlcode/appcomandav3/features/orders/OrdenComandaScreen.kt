package com.owlcode.appcomandav3.features.orders

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.domain.orders.model.ListOrdersModel
import com.owlcode.appcomandav3.ui.componet.CategoriaItem
import com.owlcode.appcomandav3.ui.componet.PedidoItem
import com.owlcode.appcomandav3.ui.componet.PlatoItem
import com.owlcode.appcomandav3.ui.componet.dialog.DialogObservaciones
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.R


@Composable
fun OrdenComandaScreen(
    navController: NavHostController,
    viewModel: OrdenComandaViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var showDialogObservacion by remember { mutableStateOf(false) }
    var positionItem by remember { mutableStateOf(-1) }

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                TextPrimary(text = "Categoria")
                LazyHorizontalGrid(
                    modifier = Modifier
                        .padding(10.dp),
                    rows = GridCells.Fixed(2)
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
                TextPrimary(text = "Platos")
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
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    ) {
                        TextPrimary(text = "Zona: Piso 1")
                        TextPrimary(text = "Mesa: 1")
                        TextPrimary(text = "Mozo: Usuario1")
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
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
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
                    ButtonPrimary(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = "Enviar",
                        onClick = {

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

}