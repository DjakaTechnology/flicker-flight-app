package id.djaka.flicker.ui.main

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.djaka.flicker.base.BaseView

interface MainView : BaseView {
    fun setBottomNavigationBehaviour(navListener : BottomNavigationView.OnNavigationItemSelectedListener)
    fun replaceFrame(f: Fragment?, i:Int)
    fun launchLogin()
}