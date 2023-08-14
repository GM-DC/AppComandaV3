package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.ui.primary.TextPrimary

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
            onClick(name)
        }
    ) {
        TextPrimary(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = name,
        )
    }
}