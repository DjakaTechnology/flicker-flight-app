package id.djaka.flicker.ui.profile


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.djaka.flicker.R
import id.djaka.flicker.base.BaseFragment
import id.djaka.flicker.base.BasePresenter
import id.djaka.flicker.model.User
import id.djaka.flicker.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.text.SimpleDateFormat
import id.djaka.flicker.util.Utill
import java.util.*

class ProfileFragment : BaseFragment<ProfilePresenter>(), ProfileView{
    override fun updateBirth(format: String?) {
        v.et_birthdate.setText(format)
    }

    override fun loadUserData(user: User) {
        v.et_name.setText(user.name)
        v.tv_name.text = user.name
        v.et_email.setText(user.email)
        v.et_phone.setText(user.phone)
        v.sp_gender.setSelection(user.genderId!! - 1)
        v.et_birthdate.setText(user.birthdate!!)
        v.et_address.setText(user.address)
    }

    override fun instantiatePresenter(): ProfilePresenter {
        return ProfilePresenter(this)
    }

    lateinit var v:View
    val myCalendar = Calendar.getInstance()!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false)

        presenter.onViewCreated(context)
        prepareOnClick()
        prepareDatePicker()
        return v
    }

    private fun prepareOnClick() {
        v.btn_save.setOnClickListener {
            presenter.saveUser(context, et_name.text.toString(),
                et_address.text.toString(),
                et_birthdate.text.toString(),
                sp_gender.selectedItemPosition + 1,
                et_phone.text.toString())
        }

        v.btn_logout.setOnClickListener { (activity as MainActivity).logout() }
    }

    private fun prepareDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            presenter.convertDateToString(myCalendar)
        }

        v.et_birthdate.setOnClickListener {
            DatePickerDialog(context,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show() }
    }
}
