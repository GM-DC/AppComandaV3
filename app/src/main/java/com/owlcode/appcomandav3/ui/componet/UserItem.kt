package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.AquaSqueeze
import com.owlcode.appcomandav3.ui.theme.Downrive

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .clickable(
                onClick = {onClick(name)},
                indication = rememberRipple(
                    bounded = true,
                    color = Downrive
                ),
                interactionSource = remember { MutableInteractionSource() }
            ),
        border = BorderStroke(2.dp,Downrive),
        colors = CardDefaults.cardColors(
            containerColor = AquaSqueeze,
        ),

    ) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TextPrimary(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = name,
                txtSize = 20.sp,
                color = Downrive,
                fontWeight = 400
            )
        }
    }
}