package br.com.fiap.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btAbout.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        btProductList.setOnClickListener{
            val intent = Intent(this, ProductList::class.java)
            startActivity(intent)
        }
        btExit.setOnClickListener{
            finish()
        }
        btLogout.setOnClickListener{
            mAuth = FirebaseAuth.getInstance()
            mAuth.signOut()
            val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean("stay_logged", false)
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}
