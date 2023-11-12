package com.owlcode.appcomandav3.ui.primary

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.owlcode.appcomandav3.ui.theme.Shark

@Composable
fun TextPrimary(
    modifier: Modifier = Modifier,
    text: String,
    txtSize: TextUnit = 14.sp,
    textAlign : TextAlign = TextAlign.Left,
    fontWeight : Int = 400,
    color : Color = Shark
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = txtSize,
        textAlign = textAlign,
        fontWeight = FontWeight(fontWeight),
        color = color,
        style = TextStyle(
            lineHeight = 18.sp
        )
    )
}