package br.com.fiap.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val newUserRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                markStayLogged(preferences)
            } else {
                markNotStayLogged(preferences)
            }
        }

        mAuth = FirebaseAuth.getInstance()

        btLogin.setOnClickListener {
            var tamanhoEmail: Int = inputLoginEmail.text.length
            var tamanhoSenha: Int = inputLoginPassword.text.length
            if (tamanhoEmail == 0) {
                Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show()
            } else{
                if (tamanhoSenha == 0) {
                    Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show()
                } else {
                    mAuth.signInWithEmailAndPassword(
                        inputLoginEmail.text.toString(),
                        inputLoginPassword.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            goToHome()
                        } else {
                            Toast.makeText(
                                this@LoginActivity, it.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
        ivLogoLogin.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java);
            startActivity(intent)
        }

        btSingIn.setOnClickListener{
            val intent = Intent(this, NewAccount::class.java);
            startActivity(intent)
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

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newUserRequestCode && resultCode == Activity.RESULT_OK) {
            inputLoginEmail.setText(data?.getStringExtra("email"))
        }
    }
}
