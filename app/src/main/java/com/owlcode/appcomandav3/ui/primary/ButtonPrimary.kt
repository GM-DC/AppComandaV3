package com.owlcode.appcomandav3.ui.primary

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.owlcode.appcomandav3.ui.theme.Downrive
import com.owlcode.appcomandav3.ui.theme.Nepal

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    txtSize: TextUnit = 14.sp,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Nepal,
            contentColor = Downrive
        ),
        onClick = { onClick() }
    ) {
        TextPrimary(
            text = text,
            txtSize = txtSize,
            fontWeight = 500
        )
    }
}