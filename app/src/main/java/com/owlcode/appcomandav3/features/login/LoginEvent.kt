package com.owlcode.appcomandav3.features.login

sealed class LoginEvent {
    object InitUser : LoginEvent()
    data class InputUser(val text: String) : LoginEvent()
    data class InputPassword(val text: String) : LoginEvent()
    object ValidatePassword : LoginEvent()
}

sealed class UIEvent {
    object GoToZones : UIEvent()
    data class ShowLoanding(val show: Boolean): UIEvent()
}