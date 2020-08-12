package com.prayertime.view.prayertime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.azan.astrologicalCalc.Location
import com.prayertime.R
import com.prayertime.utils.formatToServerDateTimeDefaults
import com.prayertime.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_location.*
import java.util.*
import javax.inject.Inject

class PrayerTimeFragment : DaggerFragment() {

    lateinit var viewModel: PrayerTimeViewModel
    lateinit var adapter: PrayerTimesAdapter
    lateinit var location: Location

    @Inject
    lateinit var providerFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModel()
        initObservables()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity(), providerFactory).get(PrayerTimeViewModel::class.java)
    }

    private fun initObservables() {
        viewModel.getLocationObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            location = Location(it.latitude, it.longitude, 1.0, 0)
            viewModel.getPrayerTimes()
            viewModel.getAddress()
        })

        viewModel.getCountryInformationObservable()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { address ->
                tv_country.text =
                    "${address.get(0).subAdminArea}, ${address.get(0).adminArea}"
            })

        viewModel.getPrayerTimesObservable()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { prayerTimes ->
                adapter.addItems(prayerTimes)
            })
    }

    private fun initView() {
        initializeRecyclerView()
        getDateInformation()
        getTimeInformation()
    }

    private fun initializeRecyclerView() {
        rv_prayer_view.layoutManager = LinearLayoutManager(activity)
        adapter = PrayerTimesAdapter()
        rv_prayer_view.adapter = adapter
    }

    private fun getDateInformation() {
        val sdf = Date().formatToServerDateTimeDefaults("EEE, MMM d")
        tv_date.text = sdf

    }

    private fun getTimeInformation() {
        val sdf = Date().formatToServerDateTimeDefaults("h:mm a")
        tv_time.text = sdf
    }
}