package id.djaka.flicker.util

import java.text.SimpleDateFormat
import java.util.*

object Utill{
    fun dateToShortDate(date: Date): String? {
        val dateFormat = SimpleDateFormat("EEE, dd MMM")
        return dateFormat.format(date)
    }
}