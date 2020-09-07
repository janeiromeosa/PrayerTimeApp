package com.prayertime.view.prayertime

import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.AzanRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PrayerTimeViewModel @Inject constructor(private val azanRepo: AzanRepo, private var geocoder: Geocoder) : ViewModel() {

    private val progressObservable = MutableLiveData<Boolean>()
    private val errorObservable: MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val gpsObservable: MutableLiveData<Location> = MutableLiveData()
    private val prayerTimeObservable: MutableLiveData<List<DataPrayerTimes>> = MutableLiveData()
    private val countryInformationObservable: MutableLiveData<List<Address>> = MutableLiveData()

    fun setLastKnowLocation(location: Location) {
        gpsObservable.value = location
    }

    fun getAddress() {
        val address = geocoder.getFromLocation(
            gpsObservable.value!!.latitude,
            gpsObservable.value!!.longitude,
            1
        )
        countryInformationObservable.value = address
    }


    fun getPrayerTimes() {
        val prayerTimes = azanRepo.getPrayerInformation(
            gpsObservable!!.value!!.latitude,
            gpsObservable!!.value!!.longitude)

        prayerTimeObservable.value = prayerTimes
    }

    fun getLocationObservable() = gpsObservable
    fun getPrayerTimesObservable() = prayerTimeObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable
    fun getCountryInformationObservable() = countryInformationObservable

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val TAG = "LocationViewModel"
    }
}
