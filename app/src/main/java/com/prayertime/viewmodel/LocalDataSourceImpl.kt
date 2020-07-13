package com.prayertime.viewmodel

import com.prayertime.data.DataLocation
import io.reactivex.Single

class LocalDataSourceImpl():LocalDataSource {
    override fun getLocationInformation(): Single<DataLocation> {
        TODO("Not yet implemented")
    }
}