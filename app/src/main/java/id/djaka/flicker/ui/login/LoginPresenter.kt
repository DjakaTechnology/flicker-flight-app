package id.djaka.flicker.ui.login

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.User
import id.djaka.flicker.util.SharedKey
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginPresenter(loginView: LoginView) : BasePresenter<LoginView>(loginView){
    var pref: SharedPreferences? = null
    var model: User? = null
    var job: Job? = null
    var dialog: ProgressDialog? = null

    @Inject
    lateinit  var apiServices:ApiServices

    var token:String = ""

    fun doLogin(email:String, password:String,c:Context){
        dialog = ProgressDialog.show(c, "",
            "Memasukkan anda...", true)

        job = CoroutineScope(Dispatchers.IO).launch{
            val request = apiServices.login(email, password)

            withContext(Dispatchers.Main){
                dialog!!.dismiss()
                try {
                    model = request.await()
                    putSharedPreferences(c, Gson().toJson(model))
                }catch (ex: Exception){
                    showAlert(c, "Email atau Password Salah", "Email atau Password anda salah silahkan coba lagi")
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