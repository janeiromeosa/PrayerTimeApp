package com.prayertime.di.app

import com.prayertime.repository.AzanRepo
import com.prayertime.repository.AzanRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(): AzanRepo {
        return AzanRepoImpl()
    }
}