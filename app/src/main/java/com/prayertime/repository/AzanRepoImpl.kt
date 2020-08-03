package com.prayertime.repository

import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes
import com.prayertime.utils.*
import java.util.*

class AzanRepoImpl: AzanRepo {

    override fun getPrayerInformation(latLng: DataLocation): List<DataPrayerTimes> {
        val today = SimpleDate(GregorianCalendar())
        val azan = Azan(
            Location(latLng.latitude, latLng.longitude),
            Method.KARACHI_SHAF
        )
        val prayerTimes = azan.getPrayerTimes(today)

        return mutableListOf(
            DataPrayerTimes(fajar, prayerTimes.fajr().toString()),
            DataPrayerTimes(zuhr, prayerTimes.fajr().toString()),
            DataPrayerTimes(asr, prayerTimes.fajr().toString()),
            DataPrayerTimes(magrib, prayerTimes.fajr().toString()),
            DataPrayerTimes(isha, prayerTimes.fajr().toString())
        )
    }
}