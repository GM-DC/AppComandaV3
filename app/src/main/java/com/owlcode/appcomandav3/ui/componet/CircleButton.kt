package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.AquaSqueeze
import com.owlcode.appcomandav3.ui.theme.Downrive

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    txtSize: TextUnit = 14.sp,
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clickable(
                onClick = {onClick()},
                indication = rememberRipple(
                    bounded = true,
                    color = Downrive
                ),
                interactionSource = remember { MutableInteractionSource() }
            )
            .size(100.dp)
            .border(width = 1.dp, color = Downrive, shape = CircleShape)
            .background(AquaSqueeze, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        TextPrimary(
            text = text,
            txtSize = txtSize,
            fontWeight = 500
        )
    }
}