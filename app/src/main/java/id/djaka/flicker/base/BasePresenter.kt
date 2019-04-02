package id.djaka.mvpanddagger.base

import id.djaka.mvpanddagger.injection.component.DaggerPresenterInjection
import id.djaka.mvpanddagger.injection.component.PresenterInjection
import id.djaka.mvpanddagger.injection.module.ContextModule

abstract class BasePresenter<out V: BaseView>(protected val view:V){
    init{
        inject()
    }

    open fun onViewCreated(){}

    open fun onViewDestroyed(){}



    private fun inject() {

    }
}