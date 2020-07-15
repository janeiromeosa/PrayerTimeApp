package com.prayertime.repository

import com.prayertime.data.DataLocation
import io.reactivex.Single

interface Repository {
   fun getLocationInformation(lat: String, lon: String): Single<DataLocation>
}
