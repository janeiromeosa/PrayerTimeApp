package com.prayertime.di.location

import com.prayertime.view.prayerTime.PrayerTimeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): PrayerTimeFragment
}