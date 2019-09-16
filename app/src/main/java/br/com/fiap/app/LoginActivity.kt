package br.com.fiap.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ivLogoLogin.setOnClickListener {
//            Toast.makeText(this@LoginActivity, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AboutActivity::class.java);
            startActivity(intent);
        }
    }
}
