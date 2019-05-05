package id.djaka.flicker.worker

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import android.app.NotificationManager
import android.app.NotificationChannel
import androidx.core.app.NotificationCompat
import androidx.work.ListenableWorker
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.injection.component.DaggerPresenterInjection
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import id.djaka.flicker.util.KEY_IMAGE_ID
import id.djaka.flicker.util.KEY_IMAGE
import java.io.File
import javax.inject.Inject
import id.djaka.flicker.util.Utill
import id.djaka.flicker.injection.module.NetworkModule
import id.djaka.flicker.R
import id.djaka.flicker.util.ProgressRequestBody


private const val LOG_TAG = "UploadWorker"
private const val KEY_ZIP_PATH = "ZIP_PATH"

class UploadWorker(appContext: Context, workerParams: WorkerParameters)
    : Worker(appContext, workerParams), ProgressRequestBody.UploadCallbacks {
    override fun onProgressUpdate(percentage: Int) {
        displayNotification("Flicker Upload", "Uploading $percentage%")
    }

    override fun onError() {
        displayNotification("Flicker Upload", "Failed")
    }

    override fun onFinish() {
        displayNotification("Flicker Upload", "Success")
    }

    @Inject
    lateinit var apiServices: ApiServices

    var uploadJob: Job? = null
    var url:String = ""

    override fun doWork(): Result {
        try {
            val id = RequestBody.create(MediaType.parse("text/plain"), inputData.getString(KEY_IMAGE_ID)!!)

            val fileToUpload = Utill.createImgPart(
                File(Utill.getRealPathFromURIPath(Uri.parse(inputData.getString(KEY_IMAGE)), applicationContext)),
                this
            )

            apiServices = NetworkModule.provideApiService(NetworkModule.provideRetrofitInterface(applicationContext), applicationContext)

            displayNotification("Flicker Uploader", "Uploading")
            doUpload(id, fileToUpload)

            val outputData = workDataOf(KEY_IMAGE to url)

            return Result.success(outputData)

        } catch (e: Exception) {
            return Result.failure()
        }
    }

    private fun displayNotification(title: String, task: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, "simplifiedcoding")
            .setContentTitle(title)
            .setContentText(task)
            .setSmallIcon(R.drawable.ic_plane)

        notificationManager.notify(1, notification.build())
    }

    private fun doUpload(id: RequestBody, fileToUpload: MultipartBody.Part){
        uploadJob = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.uploadImg( id,fileToUpload).await()

            withContext(Dispatchers.Main) {
                url = result.body()?.paymentProof!!
            }
        }
    }

}