package com.prayertime.di.location

import androidx.lifecycle.ViewModel
import com.prayertime.di.app.ViewModelKey
import com.prayertime.view.location.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindProfileViewModel(locationViewModel: LocationViewModel): ViewModel


}