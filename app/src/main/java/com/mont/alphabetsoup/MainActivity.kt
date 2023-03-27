package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        // Control d'events
        val loginButton = findViewById<Button>(R.id.BTMLOGIN);
        val registerButton = findViewById<Button>(R.id.BTMREGISTRO);
        loginButton.setOnClickListener() {
            gestionarLogin()
        }
        registerButton.setOnClickListener() {
            gestionarRegistre()
        }

    }
    override fun onStart() {
        usuariLogejat()
        super.onStart()
    }

    private fun usuariLogejat() {
        // Iniciar menu si l'usuari està loguejat
        if (user != null) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun gestionarLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun gestionarRegistre() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }


}