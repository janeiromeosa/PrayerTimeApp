package com.prayertime.view.direction

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prayertime.R
import com.prayertime.view.location.PrayerTimeViewModel.Companion.TAG
import edu.arbelkilani.compass.Compass
import edu.arbelkilani.compass.CompassListener


class DirectionFragment : Fragment() {
    lateinit var compass: Compass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_direction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Test()
    }

    fun Test(){
        compass.setListener(object : CompassListener {
            override fun onSensorChanged(event: SensorEvent) {
                Log.d(TAG, "onSensorChanged : $event")
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                Log.d(TAG, "onAccuracyChanged : sensor : $sensor")
                Log.d(TAG, "onAccuracyChanged : accuracy : $accuracy")
            }
        })
    }
}