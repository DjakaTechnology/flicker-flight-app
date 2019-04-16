package id.djaka.flicker.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import id.djaka.flicker.model.Route
import id.djaka.flicker.model.User

object SharedKey {
    object Session {
        var SESSION = "USER_SESSION_MODEL"
        var USER = "USER_JSON"
        var ID = "SESSION_ID"
        var TOKEN = "SESSION_TOKEN"
        var EMAIL = "SESSION_EMAIL"
        var REGISTERED_AT = "SESSION_REGISTERED_AT"
        var UPDATED_AT = "SESSION_UPDATED_AT"
        var NAME = "SESSION_NAME"
        var BIRTHDATE = "SESSION_BIRTHDATE"
        var PROFILE_IMG = "SESSION_PROFILE_IMG"
        var PASSWROD = "SESSION_PASSWORD"
        var IS_GUEST = "SESSION_IS_GUEST"
    }

    object App {
        var APP = "APP_MODEL"
        var IS_FIRST = "IS_FIRST_RUN_MODEL"
        var OFFLINE_MODE = "OFFLINE_MODE_MODEL"
        val TITLE: String = "TITLE"
        val ID: String = "ID"
        val MODEL:String= "MODEL"
    }

    object Result{
        var MESSAGE = "MESSAGE"
    }

    fun getUserModel(c: Context): User? {
        val pref = c.getSharedPreferences(Session.SESSION, MODE_PRIVATE)
        return try {
            Gson().fromJson<User>(pref.getString(Session.USER, null), User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun getPassanger(c: Context): Int {
        val pref = c.getSharedPreferences(Session.SESSION, MODE_PRIVATE)
        return pref.getInt(PASSANGERS.toString(), 1)
    }

    fun getRoute(c: Context): Route? {
        val pref = c.getSharedPreferences(Session.SESSION, MODE_PRIVATE)
        return try {
            Gson().fromJson<Route>(pref.getString(ROUTE, null), Route::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
