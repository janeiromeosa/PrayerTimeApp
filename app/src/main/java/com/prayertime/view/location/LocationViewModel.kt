package com.prayertime.view.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prayertime.data.DataLocation
import com.prayertime.viewmodel.Repository
import io.reactivex.disposables.CompositeDisposable

class LocationViewModel(private val repository: Repository): ViewModel(){

    private val progressObservable = MutableLiveData<Boolean>()
    private val locationObservable: MutableLiveData<DataLocation> = MutableLiveData()
    private val errorObservable:MutableLiveData<Boolean> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getIssObservable() = locationObservable
    fun getProgressObservable() = progressObservable
    fun getErrorObservable() = errorObservable



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }


}
