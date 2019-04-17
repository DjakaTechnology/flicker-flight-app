package id.djaka.flicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.djaka.flicker.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_success.*

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        btn_ok.setOnClickListener {   val i = Intent(applicationContext, MainActivity::class.java)        // Specify any activity here e.g. home or splash or login etc
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish() }
    }

    override fun onBackPressed() {
        btn_ok.performClick()
    }
}
