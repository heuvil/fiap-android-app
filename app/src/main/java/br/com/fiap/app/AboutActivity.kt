package br.com.fiap.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_login.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        tvVersionVariable.text = BuildConfig.VERSION_NAME

//        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
//        ivLogoLogin.setOnClickListener {
//            val editor = preferences.edit()
//            editor.putBoolean("stay_logged", false)
//            editor.apply()
//        }
    }
}
