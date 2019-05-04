package id.djaka.flicker.injection.component

import dagger.BindsInstance
import dagger.Component
import id.djaka.flicker.base.BaseView
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
import id.djaka.flicker.worker.UploadWorker
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface PresenterInjection{

    fun inject(airportPresenter: AirportPresenter)
    fun inject(searchPresenter: SearchPresenter)
    fun inject(loginPresenter: LoginPresenter)
    fun inject(seatPresenter: SeatPresenter)
    fun inject(ticketPresenter: TicketPresenter)
    fun inject(profilePresenter: ProfilePresenter)
    fun inject(paymentPresenter: PaymentPresenter)
    fun inject(registerPresenter: RegisterPresenter)

    @Component.Builder
    interface Builder{
        fun build(): PresenterInjection

        fun contextModule(contextModule: ContextModule): Builder
        fun networkModule(networkModule: NetworkModule):Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}