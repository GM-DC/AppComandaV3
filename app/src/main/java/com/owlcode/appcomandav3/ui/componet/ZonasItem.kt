package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.ui.primary.TextPrimary


@Composable
fun ZonasItem(
    modifier: Modifier = Modifier,
    nameZona: String,
    idZona: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 15.dp)
            .clickable {
                onClick(idZona)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextPrimary(text = nameZona)
        }
    }
}