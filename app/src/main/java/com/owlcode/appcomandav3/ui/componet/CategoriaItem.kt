package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.domain.orders.model.CategoryModel
import com.owlcode.appcomandav3.ui.primary.TextPrimary

@Composable
fun CategoriaItem(
    modifier: Modifier = Modifier,
    dataCategoria : CategoryModel,
    onClick : (CategoryModel) -> Unit
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable {
                onClick(dataCategoria)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextPrimary(text = dataCategoria.nameCategoria)
        }
    }

}