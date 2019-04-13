package id.djaka.flicker.ui.home

import android.content.Intent
import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.AirPort

interface HomeView : BaseView {
    fun updateDepart(s:String)
    fun applyTo(airport:AirPort)
    fun applyFrom(airport: AirPort)
}