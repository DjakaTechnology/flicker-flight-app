package id.djaka.flicker.base

import id.djaka.flicker.injection.component.DaggerPresenterInjection
import id.djaka.flicker.injection.component.PresenterInjection
import id.djaka.flicker.injection.module.ContextModule
import id.djaka.flicker.injection.module.NetworkModule
import id.djaka.flicker.ui.airport.AirportPresenter
import id.djaka.flicker.ui.login.LoginPresenter
import id.djaka.flicker.ui.payment_activity.PaymentPresenter
import id.djaka.flicker.ui.profile.ProfilePresenter
import id.djaka.flicker.ui.register.RegisterPresenter
import id.djaka.flicker.ui.search.SearchPresenter
import id.djaka.flicker.ui.seat.SeatPresenter
import id.djaka.flicker.ui.ticket.TicketPresenter

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
            is SeatPresenter -> injector.inject(this)
            is TicketPresenter -> injector.inject(this)
            is ProfilePresenter -> injector.inject(this )
            is PaymentPresenter -> injector.inject(this)
            is RegisterPresenter -> injector.inject(this)
        }
    }
}