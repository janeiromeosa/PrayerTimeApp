package com.prayertime.viewmodel

import com.prayertime.data.DataLocation
import io.reactivex.Single

interface Repository {
   fun getLocationInformation(lat: String, lon: String): Single<DataLocation>
}
