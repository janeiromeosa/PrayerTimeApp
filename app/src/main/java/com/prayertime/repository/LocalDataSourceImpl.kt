package com.prayertime.repository

import com.prayertime.data.DataLocation
import com.prayertime.repository.LocalDataSource
import io.reactivex.Single

class LocalDataSourceImpl(): LocalDataSource {
    override fun getLocationInformation(): Single<DataLocation> {
        TODO("Not yet implemented")
    }
}