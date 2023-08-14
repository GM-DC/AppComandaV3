package com.owlcode.appcomandav3.features.config.model

import com.owlcode.appcomandav3.domain.config.model.ConfigModel

data class StateConfig(
    val ip: String = "",
    val puerto : String = "",
    val ipImpresora: String = "",
    val config : ConfigModel = ConfigModel("","", "")
)
