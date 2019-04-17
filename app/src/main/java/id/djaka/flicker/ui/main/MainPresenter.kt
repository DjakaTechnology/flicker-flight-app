package id.djaka.flicker.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.djaka.flicker.ui.profile.ProfileFragment
import id.djaka.flicker.R
import id.djaka.flicker.ui.ticket.TicketsFragment
import id.djaka.flicker.ui.home.HomeFragment
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.util.SharedKey

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView){
    private var homeFragment: Fragment? = null
    private var ticketFragment: Fragment? = null
    private var profileFragment: Fragment? = null

    fun onViewCreated(context:Context) {
        super.onViewCreated()

        if(SharedKey.getUserModel(context) != null)
            setNavigationSelectedListener()
        else
            setNavigationSelectedListenerNotLoggedIn()
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

    private fun setNavigationSelectedListenerNotLoggedIn(){
        val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    if(homeFragment == null)
                        homeFragment = HomeFragment()
                    view.replaceFrame(homeFragment, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    view.launchLogin()
                }
                R.id.navigation_notifications -> {
                    view.launchLogin()
                }
            }
            false
        }

        view.setBottomNavigationBehaviour(navListener)
    }
}