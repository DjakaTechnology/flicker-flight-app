package id.djaka.flicker.ui.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.injection.network.ApiServices
import id.djaka.flicker.model.User
import id.djaka.flicker.util.SharedKey
import kotlinx.coroutines.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ProfilePresenter(profileView: ProfileView) : BasePresenter<ProfileView>(profileView){

    @Inject
    lateinit var apiServices: ApiServices

    var job: Job? = null

    fun onViewCreated(context: Context) {
        loadUserData(context)
    }

    private fun loadUserData(c:Context) {
        if(SharedKey.getUserModel(c) != null)
            view.loadUserData(SharedKey.getUserModel(c)!!)
    }

    fun saveUser(context: Context, name:String, address:String, birthdate:String, genderId:Int, phone:String) {
        val user = SharedKey.getUserModel(context)!!
        job = CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.updateMe(user.id!!, name, address, birthdate, genderId, phone)
            withContext(Dispatchers.Main){
                try {
                    val response = result.await()
                    response.token = user.token
                    updateUser(context, response)
                }catch (ex: Exception){
                    Log.e("TAG", ex.message)
                }
            }
        }
    }

    private fun updateUser(c: Context, response: User) {
        val editor = c.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE).edit()

        editor.putString(SharedKey.Session.USER, Gson().toJson(response))
        editor.putString(SharedKey.Session.TOKEN, response.token)
        editor.apply()

        loadUserData(c)

        Toast.makeText(c, "Berhasil memngupdate detail akun", Toast.LENGTH_SHORT).show()
    }

    fun convertDateToString(c: Calendar){
        val myFormat = "yyy-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        view.updateBirth(sdf.format(c.time))
    }

}