package com.prayertime.view.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PrayerTimeViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    init {
        Log.d(TAG, "ProfileViewModel is readyy.....")
    }

    companion object{
        val TAG = "LocationViewModel"
    }

    private val progressObservable = MutableLiveData<Boolean>()
    private val errorObservable: MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val gpsObservable: MutableLiveData<DataLocation> = MutableLiveData()
    private val prayerInfoObservable: MutableLiveData<List<DataPrayerTimes>> = MutableLiveData()


    fun setLastKnowLocation(lat: Double, lon: Double) {
        gpsObservable.postValue(DataLocation(lat, lon))
        Log.wtf(TAG, lat.toString())
        Log.wtf(TAG, lon.toString())
    }

    fun getPrayerTimes(){

    }

    fun getLocationObservable() = gpsObservable
    fun getPrayerTimesObservable() = prayerInfoObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
