package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Timer
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val logo = findViewById<ImageView>(R.id.splashImageContainer);
        Glide.with(this).load(R.drawable.splashimage).into(logo)
        moveToMainActivity()
    }

    /* Method to change Activity */
    private fun moveToMainActivity(){
        val TIME: Long=3000;
        val intent = Intent(this, MainActivity::class.java)
        Timer().schedule(TIME) {
            startActivity(intent)
        }
    }
}