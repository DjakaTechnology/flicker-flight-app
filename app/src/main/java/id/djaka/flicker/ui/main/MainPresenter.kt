package id.djaka.flicker.ui.main

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.djaka.flicker.ProfileFragment
import id.djaka.flicker.R
import id.djaka.flicker.TicketsFragment
import id.djaka.flicker.ui.home.HomeFragment
import id.djaka.mvpanddagger.base.BasePresenter

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView){
    private var homeFragment: Fragment? = null
    private var ticketFragment: Fragment? = null
    private var profileFragment: Fragment? = null

    override fun onViewCreated() {
        super.onViewCreated()

        setNavigationSelectedListener()
    }

    private fun setNavigationSelectedListener(){
        val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    if(homeFragment == null)
                        homeFragment = HomeFragment()
                    view.replaceFrame(homeFragment, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    if(ticketFragment == null)
                        ticketFragment = TicketsFragment()
                    view.replaceFrame(ticketFragment, 1)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    if(profileFragment == null)
                        profileFragment = ProfileFragment()
                    view.replaceFrame(profileFragment, 2)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

        view.setBottomNavigationBehaviour(navListener)
    }
}