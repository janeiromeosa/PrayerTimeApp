package com.example.praytime

import com.example.praytime.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApp : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .applicationBind(this)
            .build()

}
