package com.prayertime.di.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prayertime.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun providesViewModelFactory(providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
       return ViewModelFactory(providerMap)
    }
}