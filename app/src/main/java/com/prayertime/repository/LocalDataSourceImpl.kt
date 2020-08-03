package com.prayertime.repository

import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.SimpleDate
import com.prayertime.R
import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes
import io.reactivex.Single
import java.util.*

class LocalDataSourceImpl(): LocalDataSource {

    override fun getPrayerInformation(): Single<List<DataPrayerTimes>> {
        val today = SimpleDate(GregorianCalendar())
        val azan = Azan(location, Method.KARACHI_SHAF)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)
        var list: ArrayList<DataPrayerTimes> = ArrayList()
        list.add(DataPrayerTimes("Fajr", R.drawable.ic_fajr, prayerTimes.fajr().toString()))
        list.add(DataPrayerTimes("Zuhr", R.drawable.ic_dhuhr, prayerTimes.thuhr().toString()))
        list.add(DataPrayerTimes("Asr", R.drawable.ic_asr, prayerTimes.assr().toString()))
        list.add(DataPrayerTimes("Maghrib", R.drawable.ic_maghrib, prayerTimes.maghrib().toString()))
        list.add(DataPrayerTimes("Isha", R.drawable.ic_isha, prayerTimes.ishaa().toString()))
    }
}