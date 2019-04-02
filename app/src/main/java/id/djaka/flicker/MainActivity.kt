package id.djaka.flicker

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var last:Int = 0
    private var homeFragment:Fragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if(homeFragment == null)
                    homeFragment = HomeFragment()
                replaceFrame(homeFragment, 0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFrame(f: Fragment?, i:Int){
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
}
