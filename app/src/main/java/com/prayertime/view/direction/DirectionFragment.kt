package com.prayertime.view.direction

import android.content.Context
import android.content.DialogInterface
import android.hardware.*
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.prayertime.R
import com.prayertime.view.prayertime.PrayerTimeViewModel
import com.prayertime.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import edu.arbelkilani.compass.CompassListener
import kotlinx.android.synthetic.main.fragment_direction.*
import java.lang.Double
import javax.inject.Inject
import kotlin.math.roundToInt


class DirectionFragment : DaggerFragment(), CompassListener, SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor
    lateinit var magnetometer: Sensor
    lateinit var rotation: Sensor
    lateinit var viewModel: PrayerTimeViewModel
    lateinit var mLastLocation: Location

    @Inject
    lateinit var providerFactory: ViewModelFactory
    lateinit var txtAzimuth: TextView
    var changedValue: Float = 0.0f


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_direction, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        viewModel = ViewModelProvider(requireActivity(), providerFactory).get(PrayerTimeViewModel::class.java)


        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

        if (rotation == null) {
            if (accelerometer == null || magnetometer == null) {
                noSensorsFound()
            } else {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
                sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST)
            }
        } else {
            sensorManager.registerListener(this, rotation, SensorManager.SENSOR_DELAY_UI)
        }


//        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
//        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun noSensorsFound() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Your device does not support the compass")
            .setCancelable(true)
            .setNegativeButton("close") { _: DialogInterface?, _: Int ->
                //TODO?
            }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        p0?.let { handleSensorEvent(it) }
//        val degree = Math.round(p0!!.values[0])
//        val animation = RotateAnimation(changedValue, (-degree).toFloat(), Animation.RELATIVE_TO_SELF,
//            0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//
//        animation.duration = 500
//        animation.fillAfter = true
//        ic_compass.animation = animation
//
//        changedValue = -degree.toFloat()
    }

    private fun handleSensorEvent(sensorEvent: SensorEvent) {

        viewModel.getLocationObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            var head = sensorEvent.values[0].roundToInt().toFloat()
            val destinationLoc = Location("service Provider")
            destinationLoc.latitude = 21.422487 //kaaba latitude setting
            destinationLoc.longitude = 39.826206 //kaaba longitude setting

            mLastLocation = it

            var bearTo: Float = mLastLocation.bearingTo(destinationLoc)

            val geoField = GeomagneticField(
                Double.valueOf(mLastLocation.latitude).toFloat(), Double
                    .valueOf(mLastLocation.longitude).toFloat(),
                Double.valueOf(mLastLocation.altitude).toFloat(),
                System.currentTimeMillis()
            )
            head -= geoField.declination

            if (bearTo < 0) {
                bearTo += 360
            }

            var direction = bearTo - head

            if (direction < 0) {
                direction += 360
            }

            compass_needle.rotation = direction
        })


    }

    override fun onPause() {
        super.onPause()
        for (sensor in listOf(accelerometer, magnetometer, rotation)) {
            sensor?.let { sensorManager.unregisterListener(this, it) }
        }
    }

    override fun onResume(){
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_FASTEST)
    }
}