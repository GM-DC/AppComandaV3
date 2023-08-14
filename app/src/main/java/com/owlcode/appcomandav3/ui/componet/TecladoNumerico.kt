package com.owlcode.appcomandav3.ui.componet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary

@Composable
fun TecladoNumerico(
    modifier: Modifier = Modifier,
    returnText: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        TextPrimary(
            text = text
        )
        Row {
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "1"
            ) {
                text += "1"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "2") {
                text += "2"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "3") {
                text += "3"
                returnText(text)
            }
        }
        Row {
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "4") {
                text += "4"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "5") {
                text += "5"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "6") {
                text += "6"
                returnText(text)
            }
        }
        Row {
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "7") {
                text += "7"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "8") {
                text += "8"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "9") {
                text += "9"
                returnText(text)
            }
        }
        Row {
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = " ") {

            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "0") {
                text += "0"
                returnText(text)
            }
            ButtonPrimary(
                modifier = Modifier.weight(1f),
                text = "Limpiar") {
                text = ""
                returnText(text)
            }
        }
    }
}