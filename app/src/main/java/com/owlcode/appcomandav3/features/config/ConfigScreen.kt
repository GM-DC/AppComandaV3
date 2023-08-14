package com.owlcode.appcomandav3.features.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.owlcode.appcomandav3.common.ConfigNav
import com.owlcode.appcomandav3.common.LoginNav
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextFieldPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConfigScreen(
    navController: NavController,
    viewModel: ConfigViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit){
        viewModel.uiEvnet.collectLatest {
            when(it){
                UIEvent.GoToLogin -> {
                    navController.navigate(LoginNav.LoginScreen.route){

                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column{
            TextPrimary(text = "IP")
            TextFieldPrimary(
                text = state.ip,
                onValueChange = {
                    viewModel.onEvent(ConfigEvent.InputIP(it))
                }
            )
            TextPrimary(text = "Puerto")
            TextFieldPrimary(
                text = state.puerto,
                onValueChange = {
                    viewModel.onEvent(ConfigEvent.InputPuerto(it))
                }
            )
            TextPrimary(text = "IP de Impresora Pre-Cuenta")
            TextFieldPrimary(
                text = state.ipImpresora,
                onValueChange = {
                    viewModel.onEvent(ConfigEvent.InputIPImpresora(it))
                }
            )
            ButtonPrimary(text = "Guardar Dato") {
                viewModel.onEvent(ConfigEvent.GuardarDatos)
            }
        }
    }
    
}