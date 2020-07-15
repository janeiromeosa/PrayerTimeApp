package com.prayertime.di.app

import com.prayertime.di.direction.DirectionActivityModule
import com.prayertime.di.location.MainActivityModule
import com.prayertime.di.location.MainActivityScope
import com.prayertime.di.location.MainFragmentBuildersModule
import com.prayertime.view.direction.DirectionActivity
import com.prayertime.view.location.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainFragmentBuildersModule::class])
    abstract fun contributeLocationAndroidInjector(): MainActivity

    @ContributesAndroidInjector(modules = [DirectionActivityModule::class])
    abstract fun contributeDirectionAndroidInjector(): DirectionActivity


}