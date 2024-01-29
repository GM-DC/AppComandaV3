package com.owlcode.appcomandav3.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.owlcode.appcomandav3.common.LoginNav
import com.owlcode.appcomandav3.common.ZoneNav
import com.owlcode.appcomandav3.ui.componet.TecladoNumerico
import com.owlcode.appcomandav3.ui.componet.UserItem
import com.owlcode.appcomandav3.ui.primary.ButtonPrimary
import com.owlcode.appcomandav3.ui.primary.TextPrimary
import com.owlcode.appcomandav3.ui.theme.Gallery
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(Unit){
        viewModel.uiEvnet.collectLatest {
            when(it){
                is UIEvent.GoToZones -> {
                    navController.navigate(ZoneNav.ZoneScreen.route){
                        popUpTo(LoginNav.LoginScreen.route){
                            inclusive = true
                        }
                    }
                }
                is UIEvent.ShowLoanding -> {

                }
            }
        }
    }

    Row(
        Modifier
            .background(Gallery)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            TextPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = "Usuarios",
                txtSize = 40.sp,
                textAlign = TextAlign.Center
            )
            LazyVerticalGrid(
                modifier = Modifier.padding(10.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(state.listUsers){ index, user ->
                    UserItem(
                        modifier = Modifier
                            .padding(5.dp)
                            .height(100.dp),
                        name = user.nombreUsuario,
                        onClick = { userSelct ->
                            viewModel.onEvent(LoginEvent.InputUser(userSelct))
                        }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextPrimary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                text = state.user,
                txtSize = 30.sp,
                textAlign = TextAlign.Center
            )
            if(state.user.isNotBlank()){
                TecladoNumerico(
                    modifier = Modifier.padding(25.dp),
                    returnText = {
                        viewModel.onEvent(LoginEvent.InputPassword(it))
                    }
                )
                ButtonPrimary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp, vertical = 25.dp)
                        .height(70.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Ingresar",
                    txtSize = 25.sp
                ) {
                    viewModel.onEvent(LoginEvent.ValidatePassword)
                }
            }
        }
    }

}