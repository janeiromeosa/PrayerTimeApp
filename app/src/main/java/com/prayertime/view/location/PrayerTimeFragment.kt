package com.prayertime.view.location

import android.location.Address
import android.location.Geocoder
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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PrayerTimeFragment : DaggerFragment() {

    lateinit var locationViewModel: LocationViewModel
    lateinit var adapter: PrayerTimesAdapter
    lateinit var location: Location
    lateinit var geocoder: Geocoder

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
    }

    private fun onCreateComponent() {
        adapter = PrayerTimesAdapter()
    }

    private fun getObserverData() {
        locationViewModel = ViewModelProvider(requireActivity(), providerFactory).get(LocationViewModel::class.java)
        locationViewModel.getLocationObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            location = Location(it.latitude, it.longitude, 1.0, 0)
            setUpAdapter()
        })
    }

    fun setUpAdapter() {
        /*
            move lines 65 6o 68 to view model or repo @TODO
         */
        val today = SimpleDate(GregorianCalendar())
        val azan = Azan(location, Method.KARACHI_SHAF)
        val prayerTimes = azan.getPrayerTimes(today)
        val imsaak = azan.getImsaak(today)
        var list: ArrayList<DataPrayerTimes> = ArrayList()
        list.add(DataPrayerTimes("Fajr", R.drawable.ic_fajr, prayerTimes.fajr().toString()))
        list.add(DataPrayerTimes("Zuhr", R.drawable.ic_dhuhr, prayerTimes.thuhr().toString()))
        list.add(DataPrayerTimes("Asr", R.drawable.ic_asr, prayerTimes.assr().toString()))
        list.add(DataPrayerTimes("Maghrib", R.drawable.ic_maghrib, prayerTimes.maghrib().toString()))
        list.add(DataPrayerTimes("Isha", R.drawable.ic_isha, prayerTimes.ishaa().toString()))
        adapter.addItems(list)
    }

    private fun initView(){
        initializeRecyclerView()
        getObserverData()
        getCountryInformation()
        getDateInformation()
        getTimeInformation()
    }

    private fun initializeRecyclerView() {
        rv_prayer_view.layoutManager = LinearLayoutManager(activity)
        rv_prayer_view.adapter = adapter
    }

    private fun getCountryInformation(){
        geocoder = Geocoder(requireContext(), Locale.getDefault())

        locationViewModel.getLocationObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            var address: List<Address>
            address = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            tv_country_origin.text = address.get(0).subAdminArea + ", " + address.get(0).adminArea
        })
    }

    private fun getDateInformation(){
        val sdf = SimpleDateFormat("EEE, MMM d")
        val currentDate = sdf.format(Date())
        tv_country_date.text = currentDate
    }

    private fun getTimeInformation(){
        val sdf = SimpleDateFormat("h:mm a")
        val currentTime = sdf.format(Date())
        tv_country_time.text = currentTime
    }
}