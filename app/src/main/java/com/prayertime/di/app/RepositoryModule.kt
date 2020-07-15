package com.prayertime.di.app

import com.prayertime.repository.LocalDataSource
import com.prayertime.repository.LocalDataSourceImpl
import com.prayertime.repository.Repository
import com.prayertime.repository.RepositoryImpl
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

    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }
}