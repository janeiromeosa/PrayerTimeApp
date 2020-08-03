package com.prayertime.repository

import com.prayertime.data.DataPrayerTimes
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl (private val localDataSource: LocalDataSource): Repository {

    override fun getPrayerInformation(): Single<List<DataPrayerTimes>> {
        return localDataSource.getPrayerInformation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}