package com.prayertime.repository

import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes
import io.reactivex.Single

interface AzanRepo {
    fun getPrayerInformation(latLng: DataLocation): List<DataPrayerTimes>
}
