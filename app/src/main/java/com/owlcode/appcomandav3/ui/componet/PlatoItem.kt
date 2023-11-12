package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owlcode.appcomandav3.domain.orders.model.DishModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.Nepal
import com.owlcode.appcomandav3.ui.theme.SoftPeach


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
            },
        colors = CardDefaults.cardColors(
            containerColor = SoftPeach
        ),
        border = BorderStroke(2.dp,Nepal)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = dataPlato.nombre,
                txtSize = 12.sp,
                textAlign = TextAlign.Center
            )
            TextPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = "S/. ${dataPlato.preciO_VENTA}",
                txtSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}