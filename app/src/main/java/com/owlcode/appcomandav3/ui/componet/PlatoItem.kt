package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary


@Composable
fun PlatoItem(
    dataPlato : DishModel,
    onClick : (DishModel) -> Unit
) {

    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable {
                onClick(dataPlato)
            }
    ) {
        TextPrimary(text = dataPlato.nombre)
    }
}