package com.prayertime.viewmodel

import com.prayertime.data.DataLocation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl (private val localDataSource: LocalDataSource): Repository{


    override fun getLocationInformation(lat: String, lon: String): Single<DataLocation> {
        return localDataSource.getLocationInformation(lat,lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}