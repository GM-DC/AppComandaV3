package com.owlcode.appcomandav3.features.config

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.owlcode.appcomandav3.common.ConfigNav
import com.owlcode.appcomandav3.common.LoginNav
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextFieldPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import kotlinx.coroutines.flow.collectLatest
import com.owlcode.appcomandav3.R
import com.owlcode.appcomandav3.ui.theme.Gallery

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
                        popUpTo(ConfigNav.ConfigScreen.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Row(
        modifier = Modifier
            .background(Gallery)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.unosoft_logo),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                TextPrimary(
                    text = "IP",
                    fontWeight = 600,
                    txtSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldPrimary(
                    text = state.ip,
                    keyboardType = KeyboardType.Number,
                    maxDigitos = 15,
                    onValueChange = {
                        viewModel.onEvent(ConfigEvent.InputIP(it))
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextPrimary(
                    text = "Puerto",
                    fontWeight = 600,
                    txtSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldPrimary(
                    text = state.puerto,
                    keyboardType = KeyboardType.Number,
                    maxDigitos = 4,
                    onValueChange = {
                        viewModel.onEvent(ConfigEvent.InputPuerto(it))
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextPrimary(
                    text = "IP de Impresora Pre-Cuenta",
                    fontWeight = 600,
                    txtSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldPrimary(
                    text = state.ipImpresora,
                    keyboardType = KeyboardType.Number,
                    maxDigitos = 15,
                    onValueChange = {
                        viewModel.onEvent(ConfigEvent.InputIPImpresora(it))
                    }
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            ButtonPrimary(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Guardar Dato"
            ) {
                viewModel.onEvent(ConfigEvent.GuardarDatos)
            }
        }
    }
    
}