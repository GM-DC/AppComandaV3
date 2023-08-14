package com.owlcode.appcomandav3.domain.config.usecase

import com.owlcode.appcomandav3.domain.config.model.ConfigModel
import com.owlcode.appcomandav3.domain.config.repository.ConfigRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataStoreUseCase @Inject constructor(
    private val configRepository : ConfigRepository
) {
    suspend operator fun invoke():Flow<ConfigModel> {
        return configRepository.getPhoneBook()
    }
}