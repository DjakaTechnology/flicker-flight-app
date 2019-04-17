package id.djaka.flicker.ui.profile

import id.djaka.flicker.base.BaseView
import id.djaka.flicker.model.User

interface ProfileView : BaseView {
    fun loadUserData(user: User)
    fun updateBirth(format: String?)

}