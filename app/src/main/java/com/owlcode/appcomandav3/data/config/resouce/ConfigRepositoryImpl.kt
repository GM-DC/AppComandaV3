package com.owlcode.appcomandav3.data.config.resouce

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.owlcode.appcomandav3.domain.config.model.ConfigModel
import com.owlcode.appcomandav3.domain.config.repository.ConfigRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val DataStore_NAME = "PHONEBOOK"

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_NAME)

class ConfigRepositoryImpl @Inject constructor(
    private val context: Context
) : ConfigRepository {

    companion object{
        val KEYURLBASE = stringPreferencesKey("URLBASE")
        val KEYPORT = stringPreferencesKey("PORT")
        val KEYIPIMPRESORA = stringPreferencesKey("IPIMPRESORA")
    }

    override suspend fun savePhoneBook(configModel: ConfigModel) {
        context.datastore.edit { pref ->
                pref[KEYURLBASE] = configModel.urlBase
                pref[KEYPORT]= configModel.port
                pref[KEYIPIMPRESORA]= configModel.ipImpresora
        }
    }

    override suspend fun getPhoneBook() =
        context.datastore.data.map { pref ->
            ConfigModel(
                urlBase = pref[KEYURLBASE] ?: "",
                port =  pref[KEYPORT] ?: "",
                ipImpresora = pref[KEYIPIMPRESORA] ?: "",
            )
    }

}