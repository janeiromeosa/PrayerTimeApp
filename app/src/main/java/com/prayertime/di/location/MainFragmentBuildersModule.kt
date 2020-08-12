package com.prayertime.di.location

import com.prayertime.view.direction.DirectionFragment
import com.prayertime.view.prayertime.PrayerTimeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): PrayerTimeFragment

    @ContributesAndroidInjector
    abstract fun contributeDirectionFragment(): DirectionFragment
}