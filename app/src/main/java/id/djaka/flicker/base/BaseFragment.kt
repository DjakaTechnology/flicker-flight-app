package id.djaka.flicker.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseFragment<basePresenter: BasePresenter<BaseView>>: BaseView, Fragment(){
    protected lateinit var presenter : basePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    abstract fun instantiatePresenter(): basePresenter

    override fun getContext(): Context {
        return this.activity!!
    }
}