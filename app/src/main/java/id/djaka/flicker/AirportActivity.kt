package id.djaka.flicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class AirportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airport)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        return true
    }
}
