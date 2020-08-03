package com.prayertime.di.app

import com.prayertime.repository.AzanRepo
import com.prayertime.repository.AzanRepoImpl
import com.prayertime.repository.Repository
import com.prayertime.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(localDataSource: AzanRepo): Repository {
        return RepositoryImpl(
            localDataSource
        )
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(): AzanRepo {
        return AzanRepoImpl()
    }
}