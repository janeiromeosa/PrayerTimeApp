package com.prayertime.view.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.prayertime.R
import com.prayertime.view.location.LocationViewModel.Companion.TAG
import com.prayertime.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject


class LocationFragment : DaggerFragment() {

    lateinit var locationViewModel: LocationViewModel

    @Inject
    lateinit var providerFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        azan()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: Location Fragment was created")

        locationViewModel = ViewModelProvider(this, providerFactory).get(LocationViewModel::class.java)
        locationViewModel.getLocationObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {

        })
    }

    fun azan() {
        val today = SimpleDate(GregorianCalendar())
        val location = Location(30.045411, 31.236735, 2.0, 0)
        val azan = Azan(location, Method.EGYPT_SURVEY)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)
        println("----------------results------------------------")
        println("date ---> " + today.day + " / " + today.month + " / " + today.year)
        println("imsaak ---> $imsaak")
        println("Fajr ---> " + prayerTimes.fajr())
        println("sunrise --->" + prayerTimes.shuruq())
        println("Zuhr --->" + prayerTimes.thuhr())
        println("Asr --->" + prayerTimes.assr())
        println("Maghrib --->" + prayerTimes.maghrib())
        println("ISHA  --->" + prayerTimes.ishaa())
        println("----------------------------------------")

    }
}