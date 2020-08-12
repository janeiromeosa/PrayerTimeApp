package com.prayertime.repository

import com.prayertime.data.DataPrayerTimes

interface AzanRepo {
    fun getPrayerInformation(lat: Double, lon: Double): List<DataPrayerTimes>
}
