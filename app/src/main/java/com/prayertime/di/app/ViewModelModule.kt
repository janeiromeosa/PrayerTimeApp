package com.prayertime.di.app

import android.content.Context
import android.location.Geocoder
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideGeocoder(context: Context): Geocoder {
        return Geocoder(context, Locale.getDefault())
    }

}