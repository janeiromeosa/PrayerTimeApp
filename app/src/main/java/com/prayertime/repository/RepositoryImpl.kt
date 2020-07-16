package com.prayertime.repository

import com.prayertime.data.DataLocation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl (private val localDataSource: LocalDataSource): Repository {

    override fun getLocationInformation(): Single<DataLocation> {
        return localDataSource.getLocationInformation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}