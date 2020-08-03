package com.prayertime.repository

import com.prayertime.data.DataPrayerTimes
import io.reactivex.Single

interface Repository {
   fun getPrayerInformation(): Single<List<DataPrayerTimes>>
}
