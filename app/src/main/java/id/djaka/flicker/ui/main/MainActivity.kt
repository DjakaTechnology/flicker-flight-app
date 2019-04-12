package id.djaka.flicker.ui.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainView {
    private var last:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewCreated()
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
