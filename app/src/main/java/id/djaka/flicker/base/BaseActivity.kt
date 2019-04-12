package id.djaka.flicker.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<basePresenter: BasePresenter<BaseView>>: BaseView, AppCompatActivity(){
    protected lateinit var presenter : basePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    abstract fun instantiatePresenter(): basePresenter

    override fun getContext(): Context {
        return this
    }
}