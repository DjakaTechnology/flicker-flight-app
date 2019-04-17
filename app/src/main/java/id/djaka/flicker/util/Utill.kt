package id.djaka.flicker.util

import android.app.Activity
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
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
    fun getRealPathFromURIPath(contentURI: Uri, activity: Activity): String {
        val cursor = activity.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null)
            return contentURI.getPath()!!
        else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }

    fun createImgPart(file: File): MultipartBody.Part {
        val mFile = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("img", file.getName(), mFile)

        Log.e("TAG", file.getName())

        return part
    }

}