package com.prayertime.view.prayerTime

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prayertime.data.DataLocation
import com.prayertime.data.DataPrayerTimes
import com.prayertime.repository.AzanRepo
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class PrayerTimeViewModel @Inject constructor(
    private val azanRepo: AzanRepo,
    private val context: Context
) : ViewModel() {

    var geocoder: Geocoder = Geocoder(context, Locale.getDefault())

    private val progressObservable = MutableLiveData<Boolean>()
    private val errorObservable: MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val gpsObservable: MutableLiveData<DataLocation> = MutableLiveData()
    private val prayerTimeObservable: MutableLiveData<List<DataPrayerTimes>> = MutableLiveData()
    private val countryInformationObservable: MutableLiveData<List<Address>> = MutableLiveData()

    fun setLastKnowLocation(lat: Double, lon: Double) {
        gpsObservable.postValue(DataLocation(lat, lon))
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
        val prayerTimes = azanRepo.getPrayerInformation(gpsObservable.value!!)
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
