package com.prayertime.repository

import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes

interface AzanRepo {
    fun getPrayerInformation(latLng: DataLocation): List<DataPrayerTimes>
}
