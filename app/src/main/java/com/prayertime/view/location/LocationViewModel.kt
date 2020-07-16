package com.prayertime.view.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prayertime.data.DataLocation
import com.prayertime.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LocationViewModel @Inject constructor(private val repository: Repository): ViewModel(){

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

    fun setLastKnowLocation(lat: String, lon: String) {
        gpsObservable.postValue(DataLocation(lat, lon))
        Log.wtf(TAG, lat)
        Log.wtf(TAG, lon)
    }

    fun getLocationObservable() = gpsObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }
}
