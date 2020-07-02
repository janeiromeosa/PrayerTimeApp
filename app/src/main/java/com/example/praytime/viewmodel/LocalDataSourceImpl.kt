package com.example.praytime.viewmodel

import com.example.praytime.data.DataLocation
import io.reactivex.Single

class LocalDataSourceImpl():LocalDataSource {
    override fun getLocationInformation(): Single<DataLocation> {
        TODO("Not yet implemented")
    }
}