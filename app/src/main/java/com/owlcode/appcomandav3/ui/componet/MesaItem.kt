package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.domain.zones.model.TableModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.Amber
import com.owlcode.appcomandav3.ui.theme.AmberDesgrade
import com.owlcode.appcomandav3.ui.theme.DodgerBlue
import com.owlcode.appcomandav3.ui.theme.DodgerBlueDesgrade
import com.owlcode.appcomandav3.ui.theme.Scarlet
import com.owlcode.appcomandav3.ui.theme.ScarletDegrade


@Composable
fun MesaItem(
    modifier : Modifier = Modifier,
    dataMesa: TableModel,
    onClickMesa: (String) -> Unit
) {
    var color = Color.White
    var containerColor = Color.White
    when{
        dataMesa.estadoTrans == "L" && dataMesa.idPedido.isNullOrEmpty() ->{
             color = DodgerBlue
             containerColor = DodgerBlueDesgrade
        }
        dataMesa.estadoTrans == "O" && !dataMesa.idPedido.isNullOrEmpty() -> {
             color = Scarlet
             containerColor = ScarletDegrade
        }
        else -> {
             color = Amber
             containerColor = AmberDesgrade
        }
    }

    Card(
        modifier = modifier
            .width(150.dp)
            .padding(10.dp)
            .clickable {
                onClickMesa(dataMesa.idMesa.toString())
            },
        border = BorderStroke(2.dp, color),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
        ),
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