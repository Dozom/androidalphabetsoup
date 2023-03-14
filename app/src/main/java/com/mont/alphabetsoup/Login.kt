package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    lateinit var correoLogin: EditText;
    lateinit var passLogin: EditText;
    lateinit var BtnLogin: Button;
    lateinit var auth: FirebaseAuth;

    fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun LogindeJugador(email: String, passw: String) {
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val tx: String = "Benvingut " + email;
                Toast.makeText(this, tx, Toast.LENGTH_LONG).show()
                val user = auth.currentUser
                updateUI(user)
            } else {
                Toast.makeText(this, "ERROR Autentificació", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun gestionarLogin() {
        var email: String = correoLogin.getText().toString()
        var passw: String = passLogin.getText().toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            correoLogin.setError("Invalid Email")
        } else if (passw.length < 6) {
            passLogin.setError("Password less than 6 chars")
        } else {
            auth = FirebaseAuth.getInstance()
            LogindeJugador(email, passw);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        correoLogin = findViewById<EditText>(R.id.correoLogin)
        passLogin = findViewById<EditText>(R.id.passLogin)
        BtnLogin = findViewById<Button>(R.id.Btnlogin)

        BtnLogin.setOnClickListener() {
            gestionarLogin()
        }
    }

}