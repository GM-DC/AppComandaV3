package com.owlcode.appcomandav3.domain.config.usecase

import com.owlcode.appcomandav3.domain.config.model.ConfigModel
import com.owlcode.appcomandav3.domain.config.repository.ConfigRepository
import javax.inject.Inject

class SaveDataStoreUseCase @Inject constructor(
    private val configRepository : ConfigRepository
) {
    suspend operator fun invoke(urlbase:String, port:String, ipImpresora:String){
        configRepository.savePhoneBook(ConfigModel(urlbase,port, ipImpresora))
    }
}