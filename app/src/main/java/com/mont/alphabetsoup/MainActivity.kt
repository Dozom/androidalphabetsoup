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
    //per a comprovar si la sessió esta inicialitzada
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set Main Activity
        setContentView(R.layout.activity_main)
        //assigna valor a user
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        // Hide top bar
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Add buttons
        var loginButton = findViewById<Button>(R.id.BTMLOGIN);
        var registerButton = findViewById<Button>(R.id.BTMREGISTRO);

        // Add functionality to buttons
        loginButton.setOnClickListener(){
            Toast.makeText(this, "click botó login",Toast.LENGTH_LONG).show();
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener(){
            Toast.makeText(this, "click botó Registre",Toast.LENGTH_LONG).show();
            moveToRegister()
        }

    }
    // Aquest mètode s'executarà quan s'obri el menu
    override fun onStart() {
        usuariLogejat()
        super.onStart()
    }
    private fun usuariLogejat() {
        if (user != null) {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun moveToRegister() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}