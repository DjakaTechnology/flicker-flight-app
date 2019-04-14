package id.djaka.flicker.ui.detail_order

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.Route

interface DetailOrderView : BaseView {
    fun updateFlightDetail(route: Route?)

    fun updatePriceDetail(data: Route?)
}