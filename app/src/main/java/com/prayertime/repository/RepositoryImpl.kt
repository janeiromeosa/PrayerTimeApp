package com.prayertime.repository

import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes

class RepositoryImpl (private val localDataSource: AzanRepo): Repository {

    override fun getPrayerInformation(latLng: DataLocation): List<DataPrayerTimes> {
        return localDataSource.getPrayerInformation(latLng)
    }
}