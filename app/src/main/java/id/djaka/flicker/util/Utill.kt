package id.djaka.flicker.util

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import id.djaka.flicker.worker.UploadWorker
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
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date))
    }

    fun dateToHour(date:String): String? {
        val dateFormat = SimpleDateFormat("HH:mm")
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date))
    }

    fun dateToHour(date: Date): String? {
        val dateFormat = SimpleDateFormat("HH:mm")
        return dateFormat.format(date)
    }

    fun dateTimeToDate(date:String): String? {
        val dateFormat = SimpleDateFormat("yyy-MM-dd")
        return dateFormat.format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date))
    }
    fun getRealPathFromURIPath(contentURI: Uri, context: Context): String {
        val cursor = context.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null)
            return contentURI.getPath()!!
        else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }

    fun createImgPart(file: File, uploadWorker: UploadWorker): MultipartBody.Part {
        val mFile = ProgressRequestBody(file, "image", uploadWorker)
        val part = MultipartBody.Part.createFormData("img", file.getName(), mFile)

        Log.e("TAG", file.getName())

        return part
    }

}