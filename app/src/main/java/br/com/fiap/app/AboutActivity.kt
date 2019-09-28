package br.com.fiap.app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        tvVersionVariable.text = BuildConfig.VERSION_NAME

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        ivAppLogo.setOnClickListener {
            val editor = preferences.edit()
            editor.putBoolean("stay_logged", false)
            editor.apply()
            Toast.makeText(this, "variable Stay Connected set off", Toast.LENGTH_SHORT).show()
        }
    }
}
