package com.prayertime.viewmodel

import com.prayertime.data.DataLocation
import io.reactivex.Single

interface LocalDataSource {
    fun getLocationInformation(): Single<DataLocation>
}
