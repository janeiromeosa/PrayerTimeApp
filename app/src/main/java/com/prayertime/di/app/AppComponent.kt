package com.prayertime.di.app

import com.prayertime.MyApp
import com.prayertime.di.location.MainViewModelModule
import com.prayertime.di.location.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ActivityBuildersModule::class,
    RepositoryModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelFactoryModule::class,
    MainViewModelModule::class])

interface AppComponent: AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun applicationBind(myApp: MyApp): Builder

        fun build(): AppComponent
    }
}