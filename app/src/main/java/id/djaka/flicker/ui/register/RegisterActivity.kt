package id.djaka.flicker.ui.register

import android.os.Bundle
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterView {
    override fun finishActivity() {
        finish()
    }

    override fun instantiatePresenter(): RegisterPresenter {
        return RegisterPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        prepareOnClick()
    }

    private fun prepareOnClick() {
        btn_login.setOnClickListener {
            presenter.doRegister(
                et_email.text.toString(),
                et_password.text.toString(),
                et_name.text.toString(),
                this
            )
        }
    }
}