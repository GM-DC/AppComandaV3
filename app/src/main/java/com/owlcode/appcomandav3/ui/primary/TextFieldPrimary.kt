package com.owlcode.appcomandav3.ui.primary

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPrimary(
    text : String,
    onValueChange : (String) -> Unit
) {
    OutlinedTextField(
        value = text ,
        onValueChange = {
            onValueChange(it)
        }
    )
}