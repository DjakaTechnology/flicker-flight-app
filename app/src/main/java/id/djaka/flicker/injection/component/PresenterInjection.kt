package id.djaka.flicker.injection.component

import dagger.BindsInstance
import dagger.Component
import id.djaka.flicker.base.BaseView
import id.djaka.flicker.injection.module.ContextModule
import id.djaka.flicker.injection.module.NetworkModule
import id.djaka.flicker.ui.airport.AirportPresenter
import id.djaka.flicker.ui.search.SearchPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface PresenterInjection{

    fun inject(airportPresenter: AirportPresenter)
    fun inject(searchPresenter: SearchPresenter)

    @Component.Builder
    interface Builder{
        fun build(): PresenterInjection

        fun contextModule(contextModule: ContextModule): Builder
        fun networkModule(networkModule: NetworkModule):Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}