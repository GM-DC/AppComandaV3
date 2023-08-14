package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.Amber
import com.owlcode.appcomandav3.ui.theme.DodgerBlue
import com.owlcode.appcomandav3.ui.theme.Scarlet


@Composable
fun MesaItem(
    modifier : Modifier = Modifier,
    dataMesa: TableModel,
    onClickMesa: (String) -> Unit
) {
    val color = when{
        dataMesa.estadoTrans == "L" && dataMesa.idPedido.isNullOrEmpty() ->{
            DodgerBlue
        }
        dataMesa.estadoTrans == "O" && !dataMesa.idPedido.isNullOrEmpty() -> {
            Scarlet
        }
        else -> {
            Amber
        }
    }
    Card(
        modifier = modifier
            .width(150.dp)
            .padding(10.dp)
            .clickable {
                onClickMesa(dataMesa.idMesa.toString())
            },
        border = BorderStroke(2.dp, color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextPrimary(text = "Mesa ${dataMesa.idMesa}")
            TextPrimary(text = dataMesa.NombreMozo.orEmpty())
            TextPrimary(text = dataMesa.estadoTrans)
        }
    }
}