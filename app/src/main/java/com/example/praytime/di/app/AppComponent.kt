package com.example.praytime.di.app

import com.example.praytime.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityBuildersModule::class, RepositoryModule::class, AndroidSupportInjectionModule::class])

interface AppComponent: AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun applicationBind(myApp: MyApp): Builder

        fun build(): AppComponent
    }
}