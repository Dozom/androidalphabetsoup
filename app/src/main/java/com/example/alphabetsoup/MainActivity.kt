package com.example.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    var SPLASH_DURATION: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set frame layout
        setContentView(R.layout.frame_layout)

        // Allow FullScreen
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var BTMLOGIN = findViewById<Button>(R.id.BTMLOGIN);
        var BTMREGISTRO = findViewById<Button>(R.id.BTMREGISTRO);
        BTMLOGIN.setOnClickListener(){
            Toast.makeText(this, "click botó login",Toast.LENGTH_LONG).show();

        }
        BTMREGISTRO.setOnClickListener(){
            Toast.makeText(this, "click botó Registre",Toast.LENGTH_LONG).show();
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)

        }
    }

    private fun changeActivity() {
        // Change to previous Activity
        Handler().postDelayed(
            Runnable {
                val intent = Intent(this, Splash::class.java)
                startActivity(intent)
            }, SPLASH_DURATION)
    }
}