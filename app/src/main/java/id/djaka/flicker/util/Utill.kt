package id.djaka.flicker.util

import java.text.SimpleDateFormat
import java.util.*

object Utill{
    fun dateToShortDate(date: Date): String? {
        val dateFormat = SimpleDateFormat("EEE, dd MMM")
        return dateFormat.format(date)
    }

    fun dateToShortDate(date:String): String? {
        val dateFormat = SimpleDateFormat("EEE, dd MMM")
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date))
    }

    fun dateToHour(date:String): String? {
        val dateFormat = SimpleDateFormat("hh:mm")
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date))
    }

    fun dateToHour(date: Date): String? {
        val dateFormat = SimpleDateFormat("hh:mm")
        return dateFormat.format(date)
    }

    fun dateTimeToDate(date:String): String? {
        val dateFormat = SimpleDateFormat("yyy-MM-dd")
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date))
    }
}