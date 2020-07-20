package com.prayertime.view.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.prayertime.R
import com.prayertime.data.DataPrayerTimes
import com.prayertime.view.location.LocationViewModel.Companion.TAG
import com.prayertime.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_location.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class LocationFragment : DaggerFragment() {

    lateinit var locationViewModel: LocationViewModel
    lateinit var adapter: PrayerTimesAdapter

    @Inject
    lateinit var providerFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: Location Fragment was created")

        initView()

        locationViewModel = ViewModelProvider(this, providerFactory).get(LocationViewModel::class.java)
        locationViewModel.getLocationObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
        })

    }

    private fun onCreateComponent() {
        adapter = PrayerTimesAdapter()
    }

    fun azan() {
        val today = SimpleDate(GregorianCalendar())
        val location = Location(51.3140215, -0.1271871, 1.0, 0)
        val azan = Azan(location, Method.KARACHI_SHAF)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)
        println("----------------results------------------------")
        println("Time$today")
        println("date ---> " + today.day + " / " + today.month + " / " + today.year)
        println("imsaak ---> $imsaak")
        println("Fajr ---> " + prayerTimes.fajr())
        println("sunrise --->" + prayerTimes.shuruq())
        println("Zuhr --->" + prayerTimes.thuhr())
        println("Asr --->" + prayerTimes.assr())
        println("Maghrib --->" + prayerTimes.maghrib())
        println("ISHA  --->" + prayerTimes.ishaa())
        println("----------------------------------------")

        var list: ArrayList<DataPrayerTimes> = ArrayList()
        list.add(DataPrayerTimes("fajr", R.drawable.ic_fajr) )
        list.add(DataPrayerTimes("zuhr", R.drawable.ic_dhuhr))
        list.add(DataPrayerTimes("Asr", R.drawable.ic_asr))
        list.add(DataPrayerTimes("Maghrib", R.drawable.ic_maghrib))
        list.add(DataPrayerTimes("Isha", R.drawable.ic_isha))
        adapter.addItems(list)
    }

    private fun initView(){
        setUpAdapter()
        initializeRecyclerView()
        azan()
    }

    private fun setUpAdapter() {

    }

    private fun initializeRecyclerView() {
        rv_prayer_view.layoutManager = LinearLayoutManager(activity)
        rv_prayer_view.adapter = adapter
    }
}