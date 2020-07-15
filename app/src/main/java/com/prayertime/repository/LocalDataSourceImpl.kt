package com.prayertime.repository

import com.prayertime.data.DataLocation
import io.reactivex.Single

class LocalDataSourceImpl(): LocalDataSource {

    override fun getLocationInformation(lat: String, lon: String): Single<DataLocation> {
        TODO("Not yet implemented")
    }
}