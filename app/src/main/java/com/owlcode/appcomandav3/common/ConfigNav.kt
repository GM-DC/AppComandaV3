package com.owlcode.appcomandav3.common

sealed class ConfigNav(val route:String){
    object ConfigScreen : ConfigNav("config_screen")
}

sealed class LoginNav(val route:String){
    object LoginScreen : ConfigNav("login_screen")
}

sealed class ZoneNav(val route:String){
    object ZoneScreen : ConfigNav("zone_screen")
}

sealed class OrdenComandaNav(val route:String){
    object OrdenComandaScreen : ConfigNav("orden_comanda_screen")
}