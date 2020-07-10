package com.example.praytime.di.app

import com.example.praytime.viewmodel.LocalDataSource
import com.example.praytime.viewmodel.Repository
import com.example.praytime.viewmodel.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource): Repository {
        return RepositoryImpl(
            localDataSource
        )
    }
}