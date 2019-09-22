package br.com.fiap.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
//        val stayLogged = preferences.getBoolean("stay_logged", true)
        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                markStayLogged(preferences)
            } else {
                markNotStayLogged(preferences)
            }
        }
        ivLogoLogin.setOnClickListener {
//            Toast.makeText(this@LoginActivity, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AboutActivity::class.java);
            startActivity(intent);
        }
    }


    private fun markStayLogged(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("stay_logged", true)
        editor.apply()
    }
    private fun markNotStayLogged(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("stay_logged", false)
        editor.apply()
    }
}
