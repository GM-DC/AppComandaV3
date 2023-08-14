package com.owlcode.appcomandav3.domain.config.repository

import com.owlcode.appcomandav3.domain.config.model.ConfigModel
import kotlinx.coroutines.flow.Flow


interface ConfigRepository {

    suspend fun savePhoneBook(configModel: ConfigModel)

    suspend fun getPhoneBook(): Flow<ConfigModel>

}