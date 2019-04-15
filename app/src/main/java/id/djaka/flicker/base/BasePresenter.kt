package id.djaka.flicker.base

import id.djaka.flicker.injection.component.DaggerPresenterInjection
import id.djaka.flicker.injection.component.PresenterInjection
import id.djaka.flicker.injection.module.ContextModule
import id.djaka.flicker.injection.module.NetworkModule
import id.djaka.flicker.ui.airport.AirportPresenter
import id.djaka.flicker.ui.login.LoginPresenter
import id.djaka.flicker.ui.search.SearchPresenter

abstract class BasePresenter<out V: BaseView>(protected val view:V){
    private val injector: PresenterInjection = DaggerPresenterInjection
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init{
        inject()
    }

    open fun onViewCreated(){}

    open fun onViewDestroyed(){}


    private fun inject() {
        when(this){
            is AirportPresenter -> injector.inject(this)
            is SearchPresenter -> injector.inject(this)
            is LoginPresenter -> injector.inject(this)
        }
    }
}