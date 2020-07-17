package com.prayertime.view.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azan.Azan
import com.azan.Method
import com.azan.astrologicalCalc.Location
import com.azan.astrologicalCalc.SimpleDate
import com.prayertime.R
import com.prayertime.data.DataPrayerTimes
import com.prayertime.view.location.LocationViewModel.Companion.TAG
import com.prayertime.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

class LocationFragment : DaggerFragment() {

    private val dummyList = listOf(,
        DataPrayerTimes("fajr", R.drawable.ic_fajr),
        DataPrayerTimes("zuhr", R.drawable.ic_dhuhr),
        DataPrayerTimes("Asr", R.drawable.ic_asr),
        DataPrayerTimes("Maghrib", R.drawable.ic_maghrib),
        DataPrayerTimes("Isha", R.drawable.ic_isha),
    )

    lateinit var locationViewModel: LocationViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PrayerTimesAdapter
    protected lateinit var rootView: View

    @Inject
    lateinit var providerFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_location, container, false)
        initView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: Location Fragment was created")

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

        var list: ArrayList<DataPrayerTimes> = ArrayList<DataPrayerTimes>()
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
////       var user = adapter.getItem(position)
//                startActivity(context?.let {ctx ->
//                    user?.let {
//                            user -> MainActivity.newIntent(ctx, user)
//                    }
//                })
    }

    private fun initializeRecyclerView() {
        recyclerView = rootView.findViewById(R.id.rv_prayer_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun setUpDummyData(){
//        val today = SimpleDate(GregorianCalendar())
//        val location = Location(51.3140215, -0.1271871, 1.0, 0)
//        val azan = Azan(location, Method.KARACHI_SHAF)
//        val prayerTimes = azan.getPrayerTimes(today)
//        val imsaak = azan.getImsaak(today)
//        var list: ArrayList<DataPrayerTimes> = ArrayList<DataPrayerTimes>()
//        list.add(DataPrayerTimes("Fajr", fajr ))
//        list.add(User("User 2", R.drawable.user))
//        list.add(User("User 3", R.drawable.user))
//        list.add(User("User 4", R.drawable.user))
//        list.add(User("User 5", R.drawable.user))
//
//        adapter.addItems(list)
    }
}