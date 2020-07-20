package com.prayertime.utils

import android.location.Location
import java.util.*

class AppUtils {
    private val TAG = AppUtils::class.java.simpleName

    fun getTime(): String? {
        val hour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
        val minute = Calendar.getInstance()[Calendar.MINUTE]
        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
    }

    fun convert(latitude: Double, longitude: Double): String? {
        val builder = StringBuilder()
        builder.append("Lat.  ")
        if (latitude < 0) {
            builder.append("S ")
        } else {
            builder.append("N ")
        }
        val latitudeDegrees = Location.convert(
            Math.abs(latitude),
            Location.FORMAT_SECONDS
        )
        val latitudeSplit =
            latitudeDegrees.split(":".toRegex()).toTypedArray()
        builder.append(latitudeSplit[0])
        builder.append("°")
        builder.append(latitudeSplit[1])
        builder.append("'")
        builder.append(latitudeSplit[2])
        builder.append("\"")
        builder.append("\n")
        builder.append("Lng. ")
        if (longitude < 0) {
            builder.append("W ")
        } else {
            builder.append("E ")
        }
        val longitudeDegrees = Location.convert(
            Math.abs(longitude),
            Location.FORMAT_SECONDS
        )
        val longitudeSplit =
            longitudeDegrees.split(":".toRegex()).toTypedArray()
        builder.append(longitudeSplit[0])
        builder.append("°")
        builder.append(longitudeSplit[1])
        builder.append("'")
        builder.append(longitudeSplit[2])
        builder.append("\"")
        return builder.toString()
    }

}