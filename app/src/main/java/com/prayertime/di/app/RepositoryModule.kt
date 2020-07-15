package com.prayertime.di.app

import com.prayertime.viewmodel.LocalDataSource
import com.prayertime.viewmodel.LocalDataSourceImpl
import com.prayertime.viewmodel.Repository
import com.prayertime.viewmodel.RepositoryImpl
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