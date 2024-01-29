package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.owlcode.appcomandav3.ui.primary.TextPrimary

@Composable
fun TecladoNumerico(
    modifier: Modifier = Modifier,
    returnText: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextPrimary(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            txtSize = 25.sp,
            fontWeight = 500,
            textAlign = TextAlign.Center
        )
        Row {
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .height(70.dp),
                txtSize = 18.sp,
                text = "1"
            ) {
                text += "1"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "2") {
                text += "2"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "3") {
                text += "3"
                returnText(text)
            }
        }
        Row {
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "4") {
                text += "4"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "5") {
                text += "5"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "6") {
                text += "6"
                returnText(text)
            }
        }
        Row {
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,

                text = "7") {
                text += "7"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "8") {
                text += "8"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "9") {
                text += "9"
                returnText(text)
            }
        }
        Row {
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = " ") {

            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "0") {
                text += "0"
                returnText(text)
            }
            CircleButton(
                modifier = Modifier
                    .padding(5.dp)
                    .height(70.dp)
                    .weight(1f),
                txtSize = 18.sp,
                text = "Limpiar") {
                text = ""
                returnText(text)
            }
        }
    }
}

@Preview
@Composable
fun TecladoNumericoPreview() {
    TecladoNumerico(
        modifier = Modifier,
        returnText = {

        }
    )
}