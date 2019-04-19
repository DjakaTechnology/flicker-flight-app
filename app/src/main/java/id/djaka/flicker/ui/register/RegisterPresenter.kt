package id.djaka.flicker.ui.register

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import com.google.gson.Gson
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.User
import id.djaka.flicker.util.SharedKey
import kotlinx.coroutines.*
import java.util.regex.Matcher
import javax.inject.Inject

class RegisterPresenter(registerView: RegisterView) : BasePresenter<RegisterView>(registerView){
    var pref: SharedPreferences? = null
    var model: User? = null
    var job: Job? = null
    var dialog: ProgressDialog? = null

    @Inject
    lateinit  var apiServices:ApiServices

    var token:String = ""

    fun doRegister(email:String, password:String, name:String,c:Context){
        if(email == "" || password == "" || name == ""){
            showAlert(c, "Periksa input", "Pastikan anda sudah mengisi form dengan benar")
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showAlert(c, "Kesalahan email", "Isi alamat email dengan benar")
            return
        }

        dialog = ProgressDialog.show(c, "",
            "Membuat akun...", true)

        job = CoroutineScope(Dispatchers.IO).launch{
            val request = apiServices.register(name, email, password)

            withContext(Dispatchers.Main){
                dialog!!.dismiss()
                try {
                    model = request.await()
                    putSharedPreferences(c, Gson().toJson(model))
                }catch (ex: Exception){
                    showAlert(c, "Terjadi keasalah", "Email sudah digunakan")
                }
            }
        }

    }

    private fun putSharedPreferences(c:Context, json: String) {
        val editor = c.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE).edit()

        editor.putString(SharedKey.Session.USER, json)
        editor.putString(SharedKey.Session.TOKEN, model!!.token)
        editor.apply()

        startMainActivity(c)
    }

    private fun startMainActivity(c:Context) {
//        val i = Intent(c, MainActivity::class.java)
//        c.startActivity(i)
        (c as Activity).setResult(Activity.RESULT_OK)
        c.finish()
    }

    fun showAlert(c: Context, title:String, message:String) {
        val alertDialog = android.app.AlertDialog.Builder(c).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }
}