package com.owlcode.appcomandav3.di

import com.owlcode.appcomandav3.data.config.resouce.ConfigRepositoryImpl
import com.owlcode.appcomandav3.domain.config.repository.ConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindConfigRepository(impl: ConfigRepositoryImpl): ConfigRepository
}