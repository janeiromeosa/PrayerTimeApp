package com.prayertime.repository

import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.prayertime.data.DataPrayerTimes
import com.prayertime.utils.*
import java.util.*

class AzanRepoImpl: AzanRepo {

    override fun getPrayerInformation(lat: Double, lon: Double): List<DataPrayerTimes> {
        val today = SimpleDate(GregorianCalendar())
        val azan = Azan(Location(lat, lon, 1.0, 1), Method.KARACHI_SHAF)
        val prayerTimes = azan.getPrayerTimes(today)

        return mutableListOf(
            DataPrayerTimes(fajar, prayerTimes.fajr().toString()),
            DataPrayerTimes(zuhr, prayerTimes.thuhr().toString()),
            DataPrayerTimes(asr, prayerTimes.assr().toString()),
            DataPrayerTimes(magrib, prayerTimes.maghrib().toString()),
            DataPrayerTimes(isha, prayerTimes.ishaa().toString())
        )
    }
}