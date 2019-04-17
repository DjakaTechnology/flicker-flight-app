package id.djaka.flicker.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.ui.login.LoginActivity
import id.djaka.flicker.util.LOGIN
import id.djaka.flicker.util.SharedKey
import kotlinx.android.synthetic.main.activity_detail_order.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {
    private var last:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewCreated(this)
//        val pref:SharedPreferences = this.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE)
//        pref.edit().clear().apply()
    }

    override fun launchLogin(){
        val i = Intent(this, LoginActivity::class.java)
        startActivityForResult(i, LOGIN)
    }

    override fun instantiatePresenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun setBottomNavigationBehaviour(navListener : BottomNavigationView.OnNavigationItemSelectedListener) {
        bottom_nav.setOnNavigationItemSelectedListener(navListener)
        bottom_nav.selectedItemId = R.id.navigation_home
    }

    override fun replaceFrame(f: Fragment?, i:Int){
        val transaction = supportFragmentManager.beginTransaction()
        if (last > i)
            transaction.setCustomAnimations(
                R.animator.enter_from_left,
                R.animator.exit_to_right
            )
        else
            transaction.setCustomAnimations(
                R.animator.enter_from_right,
                R.animator.exit_to_left
            )
        if (f != null) {
            transaction.replace(R.id.frame_container, f)
        }
        transaction.addToBackStack(null)
        transaction.commit()

        last = i
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == LOGIN && resultCode == Activity.RESULT_OK)
            recreate()
    }

    fun logout(){
        val pref:SharedPreferences = this.getSharedPreferences(SharedKey.Session.SESSION, Context.MODE_PRIVATE)
        pref.edit().clear().apply()
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
