package com.example.alphabetsoup

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.util.*
import kotlin.concurrent.schedule

class Splash : AppCompatActivity() {
    private val duracio: Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Allow fullscreen
        supportActionBar?.hide()

        // Set alien Splash Screen
        val logo = findViewById<ImageView>(R.id.splashLogo)
        Glide.with(this).load(R.drawable.alien).into(logo)

        // Change activity
        canviarActivity()
    }

    private fun canviarActivity(){
        Timer().schedule(duracio) {
            saltaInici()
        }
    }

    fun saltaInici(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}