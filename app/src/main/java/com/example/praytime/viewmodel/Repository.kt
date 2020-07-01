package com.example.praytime.viewmodel

import com.example.praytime.data.DataLocation
import io.reactivex.Single

interface Repository {
   fun getLocationInformation(): Single<DataLocation>
}
