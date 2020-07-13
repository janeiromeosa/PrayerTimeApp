package com.prayertime.viewmodel

import com.prayertime.data.DataLocation
import io.reactivex.Single

interface Repository {
   fun getLocationInformation(): Single<DataLocation>
}
