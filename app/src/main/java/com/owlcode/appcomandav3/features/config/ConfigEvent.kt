package com.owlcode.appcomandav3.features.config

sealed class ConfigEvent {
    object GuardarDatos : ConfigEvent()
    object InitData : ConfigEvent()
    data class InputIP(val text : String) : ConfigEvent()
    data class InputPuerto(val text : String) : ConfigEvent()
    data class InputIPImpresora(val text : String) : ConfigEvent()
}

sealed class UIEvent {
    object GoToLogin : UIEvent()
}
