package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Timer
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {
    private val TIME: Long=3000;
    override fun onCreate(savedInstanceState: Bundle?) {
        // Set splash view
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Hide android top bar
        supportActionBar?.hide()
        // Set image to splash
        val logo = findViewById<ImageView>(R.id.splashImageContainer);
        Glide.with(this).load(R.drawable.splashimage).into(logo)
        // Change Activity Method
        changeActivity()
    }

    private fun changeActivity(){
        Timer().schedule(TIME) {
            moveToMainActivity()
        }
    }

    fun moveToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}