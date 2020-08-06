package com.prayertime.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatToServerDateTimeDefaults(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(this)
}

const val fajar  = "Fajar"
const val zuhr  = "Zuhr"
const val asr  = "Asr"
const val magrib  = "Magrib"
const val isha  = "Isha"
//"EEE, MMM d"