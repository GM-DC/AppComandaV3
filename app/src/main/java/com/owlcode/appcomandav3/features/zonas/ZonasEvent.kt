package com.owlcode.appcomandav3.features.zonas

sealed class ZonasEvent {
    object InitZona : ZonasEvent()
    data class InitMesa(val idMesa: String) : ZonasEvent()
    object CancelarCorrutine : ZonasEvent()
    data class SelectZona(val item: String) : ZonasEvent()
    data class SelectMesa(val item: String) : ZonasEvent()
}


sealed class UIEvent {
    object GoToPedido : UIEvent()
}