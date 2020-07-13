package com.prayertime.viewmodel

import com.prayertime.data.DataLocation
import io.reactivex.Single

class RepositoryImpl (private val localDataSource: LocalDataSource): Repository{
    override fun getLocationInformation(): Single<DataLocation> {
        TODO("Not yet implemented")
        //make calls from the library here
    }

}