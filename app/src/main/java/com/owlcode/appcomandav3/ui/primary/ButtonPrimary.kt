package com.owlcode.appcomandav3.ui.primary

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    txtSize: TextUnit = 14.sp,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        TextPrimary(
            text = text,
            txtSize = txtSize,
        )
    }
}