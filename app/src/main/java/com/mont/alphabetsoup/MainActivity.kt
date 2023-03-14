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

    var SPLASH_DURATION: Long = 3000;
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;

    private fun usuariLogejat() {
        // Iniciar menu si l'usuari està loguejat
        if (user != null) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        usuariLogejat()
        super.onStart()
    }

    private fun gestionarLogin() {
        // Enviar al controller del login
        Toast.makeText(this, "click botó Login", Toast.LENGTH_LONG).show();
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun gestionarRegistre() {
        Toast.makeText(this, "click botó Registre", Toast.LENGTH_LONG).show();
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //assigna valor a user
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        // Obtenir els botons de la vista
        var loginButton = findViewById<Button>(R.id.BTMLOGIN);
        var registerButton = findViewById<Button>(R.id.BTMREGISTRO);

        // Vincular els botons de la vista a una acció
        loginButton.setOnClickListener() {
            gestionarLogin()
        }
        registerButton.setOnClickListener() {
            gestionarRegistre()
        }

    }
}