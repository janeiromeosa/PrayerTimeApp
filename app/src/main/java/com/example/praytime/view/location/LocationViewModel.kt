package com.example.praytime.view.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praytime.data.DataLocation
import com.example.praytime.viewmodel.Repository
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
