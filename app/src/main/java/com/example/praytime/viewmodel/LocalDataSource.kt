package com.example.praytime.viewmodel

import com.example.praytime.data.DataLocation
import io.reactivex.Single

interface LocalDataSource {
    fun getLocationInformation(): Single<DataLocation>
}
