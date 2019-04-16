package id.djaka.flicker.ui.seat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.ui.login.LoginPresenter
import id.djaka.flicker.ui.login.LoginView

class SeatActivity : BaseActivity<SeatPresenter>(), SeatView {
    override fun instantiatePresenter(): SeatPresenter {
        return SeatPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)
    }
}
