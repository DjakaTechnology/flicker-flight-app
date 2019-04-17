package id.djaka.flicker.ui.payment_activity

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.util.Log
import android.widget.Toast
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.Reservation
import kotlinx.coroutines.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

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


    fun doUpload(id:RequestBody, fileToUpload: MultipartBody.Part) {
        uploadJob = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.uploadImg( id,fileToUpload).await()

            withContext(Dispatchers.Main) {
                if (result.isSuccessful) onSucceed(result.body()!!)
                else Log.e("UPLOAD", result.errorBody().toString())
            }
        }
    }

    private fun onSucceed(body: Reservation) {
        view.showImage(body)
    }
}