package com.prayertime.repository

import com.prayertime.data.DataLocation
import io.reactivex.Single

interface LocalDataSource {
    fun getLocationInformation(): Single<DataLocation>
}
