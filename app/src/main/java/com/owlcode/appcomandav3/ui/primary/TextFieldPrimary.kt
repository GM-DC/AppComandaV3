package com.owlcode.appcomandav3.ui.primary

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPrimary(
    text : String,
    onValueChange : (String) -> Unit,
    maxDigitos: Int = 300,
    keyboardType : KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        modifier = Modifier.background(Color.White),
        value = text,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        onValueChange = {
            if (text.length <= maxDigitos){
                onValueChange(it)
            }
        },
        singleLine = true
    )
}