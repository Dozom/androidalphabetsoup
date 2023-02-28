package com.example.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    var SPLASH_DURATION: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FullScreen
        supportActionBar?.hide()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // get Image View
        val logo = findViewById<ImageView>(R.id.splashLogo);

        Glide.with(this).load(R.drawable.alien).into(logo)

        // Importar el so al splash
//        val MEDIAPLAYER = MediaPlayer.create(this, R.raw.soareproduir);
  //      MEDIAPLAYER.setVolume(100f, 100f);


    //    Timer().schedule(SPLASH_DURATION){
            changeActivity()
     //   }
    }

    private fun changeActivity() {
        Handler().postDelayed(
            Runnable {
                val intent = Intent(this, Splash::class.java)
                startActivity(intent)
            }, SPLASH_DURATION)
        //val INTENT = Intent(this, StartScreen::class.java)
        //startActivity(INTENT)
    }
}