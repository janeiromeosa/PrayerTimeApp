package com.prayertime.view.location

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
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
    private val locationObservable: MutableLiveData<DataLocation> = MutableLiveData()
    private val errorObservable:MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var location : MutableLiveData<Location> = MutableLiveData()


    fun getGPSCordinates(lat: String, lon: String) {
        compositeDisposable.add(
            repository.getLocationInformation(lat, lon)
                .doOnSubscribe { progressObservable.postValue(true) }
                .doOnError { progressObservable.value = false }
                .subscribe({ value ->
                    locationObservable.value = value
                    progressObservable.value = false
                }, { errorObservable.value = true })
        )
    }

    fun getLocation(fused: FusedLocationProviderClient) {
        fused!!.lastLocation
            .addOnSuccessListener {loc: Location? ->
                location.value = loc
            }
    }

    fun getIssObservable() = locationObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable
    fun getLocationObserable() = location



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }


}
