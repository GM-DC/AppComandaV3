package com.owlcode.appcomandav3.ui.componet.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextFieldPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.BlackTransparent

@Composable
fun DialogObservaciones(
    show: Boolean = false,
    text : String,
    onValueChange: (String) -> Unit,
    onClickGuardar: () -> Unit,
    onClickCancelar: () -> Unit,
) {
    if(show){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackTransparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { }
                )
        ) {
            Card(
                modifier = Modifier.padding(100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    TextPrimary(
                        text = "Agregar Observacion"
                    )
                    TextFieldPrimary(
                        text = text,
                        onValueChange = {
                            onValueChange(it)
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ButtonPrimary(
                            text = "Guardar",
                            onClick = {
                                onClickGuardar()
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        ButtonPrimary(
                            text = "Cancelar",
                            onClick = {
                                onClickCancelar()
                            }
                        )
                    }

                }
            }
        }
    }

}