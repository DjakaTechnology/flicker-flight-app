package id.djaka.flicker.ui.payment_activity

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.Reservation
import id.djaka.flicker.worker.UploadWorker
import id.djaka.flicker.util.KEY_IMAGE_ID
import id.djaka.flicker.util.KEY_IMAGE
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject
import java.util.concurrent.TimeUnit


class PaymentPresenter(paymentView: PaymentView) : BasePresenter<PaymentView>(paymentView){
    companion object {
        const   val REQUEST_GALLERY_CODE = 200
        const val READ_REQUEST_CODE = 300
    }

    lateinit var activity:PaymentActivity

    @Inject
    lateinit var apiServices: ApiServices

    var uploadJob: Job? = null

    fun uploadImg(paymentActivity: PaymentActivity) {
        this.activity = paymentActivity
        showGallery()
    }

    private fun onSucceedSubmit(paymentActivity: PaymentActivity) {
        Toast.makeText(paymentActivity, "Berhasil diupload", Toast.LENGTH_SHORT).show()
    }

    @AfterPermissionGranted(READ_REQUEST_CODE)
    fun showGallery() {
        if (EasyPermissions.hasPermissions(activity, READ_EXTERNAL_STORAGE)) {
            val i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            activity.startActivityForResult(i, REQUEST_GALLERY_CODE)
        } else {
            EasyPermissions.requestPermissions(activity, "Required Permission", READ_REQUEST_CODE, READ_EXTERNAL_STORAGE)
        }
    }


    fun doUpload(id:String, fileToUpload: Uri) {
        //This is the subclass of our WorkRequest
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val setData = workDataOf(KEY_IMAGE_ID to id, KEY_IMAGE to fileToUpload.toString())

        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            .setConstraints(constraints)
            .setInputData(setData)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance().enqueue(uploadWorkRequest)
        WorkManager.getInstance().getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(activity, Observer { workInfo ->
                // Check if the current work's state is "successfully finished"
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    val res = Reservation()
                    res.paymentProof = workInfo.outputData.getString(KEY_IMAGE)
                    view.showImage(res)
                    Log.d("SUCCESS", "SUCCESS")
                }
            })
    }

    private fun onSucceed(body: Reservation) {
        view.showImage(body)
    }
}