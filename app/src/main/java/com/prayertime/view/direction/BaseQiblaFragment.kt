//package com.prayertime.view.direction
//
//import android.os.Bundle
//import android.view.View
//import androidx.lifecycle.ViewModelProvider
//import com.prayertime.view.prayertime.PrayerTimeViewModel
//import com.prayertime.viewmodel.ViewModelFactory
//import dagger.android.support.DaggerFragment
//import javax.inject.Inject
//
//abstract class BaseQiblaFragment : DaggerFragment() {
//
//    val TAG: String = "AppDebug"
//
//    @Inject
//    lateinit var providerFactory: ViewModelFactory
//
//    lateinit var mainViewModel: PrayerTimeViewModel
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        mainViewModel = activity?.run {
//            ViewModelProvider(requireActivity(), providerFactory).get(PrayerTimeViewModel::class.java)
//        }?: throw Exception("Invalid activity")
//
//
//    }
//
//}