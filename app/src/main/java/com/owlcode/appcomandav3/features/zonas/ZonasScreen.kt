package com.owlcode.appcomandav3.features.zonas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.owlcode.appcomandav3.common.OrdenComandaNav
import com.owlcode.appcomandav3.common.ZoneNav
import com.owlcode.appcomandav3.common.utils.Companion.DATA_TABLE
import com.owlcode.appcomandav3.common.utils.Companion.DATA_ZONA
import com.owlcode.appcomandav3.common.utils.Companion.NOMBRE_MOZO
import com.owlcode.appcomandav3.common.utils.Companion.ZONA
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.ui.componet.MesaItem
import com.owlcode.appcomandav3.ui.componet.ZonasItem
import com.owlcode.appcomandav3.ui.primary.TextPrimary

@Composable
fun ZonasScreen(
    navController: NavHostController,
    viewModel: ZonasViewModel = hiltViewModel()
) {
    val listazona = viewModel.state.value.listaZona
    val listaMesa = viewModel.state.value.listaMesa



    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TextPrimary(
                text = "Zonas",
                txtSize = 20.sp
            )
            LazyRow {
                itemsIndexed(listazona) {  index, zonas ->
                    ZonasItem(
                        modifier = Modifier,
                        nameZona = zonas.nombreZonas,
                        idZona = zonas.idZona,
                        onClick = {
                            DATA_ZONA = zonas
                            viewModel.onEvent(ZonasEvent.CancelarCorrutine)
                            viewModel.onEvent(ZonasEvent.InitMesa(it))
                        }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
        ) {
            TextPrimary(
                text = "Mesas",
                txtSize = 20.sp
            )
            LazyHorizontalGrid(
                modifier = Modifier
                    .padding(10.dp),
                rows = GridCells.Fixed(5)
            ){
                itemsIndexed(listaMesa){ index: Int, mesa: TableModel ->
                    MesaItem(
                        dataMesa = mesa,
                        onClickMesa = {
                            viewModel.onEvent(ZonasEvent.CancelarCorrutine)
                            NOMBRE_MOZO = mesa.NombreMozo.orEmpty()
                            DATA_TABLE = mesa
                            navController.navigate(OrdenComandaNav.OrdenComandaScreen.route){
                                popUpTo(ZoneNav.ZoneScreen.route){
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }

    }
}