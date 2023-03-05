package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var SPLASH_DURATION: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set Main Activity
        setContentView(R.layout.activity_main)

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

    private fun moveToRegister() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }
}