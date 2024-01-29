package com.owlcode.appcomandav3.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.owlcode.appcomandav3.common.ConfigNav
import com.owlcode.appcomandav3.common.LoginNav
import com.owlcode.appcomandav3.common.OrdenComandaNav
import com.owlcode.appcomandav3.common.ZoneNav
import com.owlcode.appcomandav3.features.config.ConfigScreen
import com.owlcode.appcomandav3.features.login.LoginScreen
import com.owlcode.appcomandav3.features.orders.OrdenComandaScreen
import com.owlcode.appcomandav3.features.zonas.ZonasScreen
import com.owlcode.appcomandav3.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Scaffold {
                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            startDestination = ConfigNav.ConfigScreen.route
                        ){
                            composable(route = ConfigNav.ConfigScreen.route){
                                ConfigScreen(navController)
                                //LoginScreen(navController)
                            }
                            composable(route = LoginNav.LoginScreen.route){
                                LoginScreen(navController)
                            }
                            composable(route = ZoneNav.ZoneScreen.route){
                                ZonasScreen(navController)
                            }
                            composable(route = OrdenComandaNav.OrdenComandaScreen.route){
                                OrdenComandaScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
