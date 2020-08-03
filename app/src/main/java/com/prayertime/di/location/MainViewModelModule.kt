package com.prayertime.di.location

import androidx.lifecycle.ViewModel
import com.prayertime.di.app.ViewModelKey
import com.prayertime.view.prayerTime.PrayerTimeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PrayerTimeViewModel::class)
    abstract fun bindProfileViewModel(prayerTimeViewModel: PrayerTimeViewModel): ViewModel


}