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
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.Nepal
import com.owlcode.appcomandav3.ui.theme.SoftPeach

@Composable
fun CategoriaItem(
    modifier: Modifier = Modifier,
    dataCategoria : CategoryModel,
    onClick : (CategoryModel) -> Unit
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .padding(5.dp)
            .clickable {
                onClick(dataCategoria)
            },
        colors = CardDefaults.cardColors(
            containerColor = SoftPeach
        ),
        border = BorderStroke(2.dp,Nepal)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 5.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = dataCategoria.nameCategoria,
                textAlign = TextAlign.Center,
            )
        }
    }

}