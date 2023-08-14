package com.owlcode.appcomandav3.di

import android.content.Context
import com.owlcode.appcomandav3.data.config.resouce.ConfigRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModel {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)= ConfigRepositoryImpl(context)

}