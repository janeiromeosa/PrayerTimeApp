package com.example.praytime.di.app

import com.example.praytime.di.direction.DirectionActivityModule
import com.example.praytime.di.location.LocationActivityModule
import com.example.praytime.di.location.LocationScope
import com.example.praytime.view.direction.DirectionActivity
import com.example.praytime.view.location.LocationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @LocationScope
    @ContributesAndroidInjector(modules = [(LocationActivityModule::class)])
    abstract fun contributeLocationAndroidInjector(): LocationActivity

    @ContributesAndroidInjector(modules = [(DirectionActivityModule::class)])
    abstract fun contributeDirectionAndroidInjector(): DirectionActivity


}