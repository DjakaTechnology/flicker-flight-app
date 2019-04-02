package id.djaka.mvpanddagger.injection.component

import dagger.BindsInstance
import dagger.Component
import id.djaka.mvpanddagger.base.BaseView
import id.djaka.mvpanddagger.injection.module.ContextModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class])
interface PresenterInjection{

    @Component.Builder
    interface Builder{
        fun build():PresenterInjection

        fun contextModule(contextModule: ContextModule):Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}