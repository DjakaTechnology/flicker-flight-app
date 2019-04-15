package id.djaka.flicker.ui.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import id.djaka.flicker.base.BasePresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {
    override fun finishActivity() {
        finish()
    }

    override fun instantiatePresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prepareOnClick()
    }

    private fun prepareOnClick() {
        btn_login.setOnClickListener { presenter.doLogin(et_email.text.toString(), et_password.text.toString(), this) }
    }
}
