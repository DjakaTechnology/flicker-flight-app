package id.djaka.flicker.ui.search

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.AirPort
import id.djaka.flicker.model.Route

interface SearchView : BaseView {
    fun updateRoute(data:List<Route>, passanger:Int)
    fun updateText(from: AirPort?, to: AirPort?, depart: String?, passanger: Int)
}