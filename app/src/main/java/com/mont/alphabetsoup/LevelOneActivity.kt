package com.mont.alphabetsoup

import android.R.attr.button
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class LevelOneActivity : AppCompatActivity() {
    lateinit var v00: ImageView
    lateinit var countTime: TextView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_one)
        var letterPanel: GridLayout = findViewById<GridLayout>(R.id.letterPanel)
        countTime = findViewById(R.id.timerText)
        startTimeCounter()
        for (x in 0..5) {
            for (y in 0..7) {
                var l: Letter = Letter(this)
                l.setColor()
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    GridLayout.LayoutParams.WRAP_CONTENT,
                    GridLayout.LayoutParams.WRAP_CONTENT
                )
                l.layoutParams = params
                l.gravity = Gravity.CENTER_HORIZONTAL
                l.text = "X"
                l.id = x
                letterPanel.addView(l)
                l.setOnClickListener {
                    l.setColorRed()
                }
            }
        }

/*        val var00 :ImageView = findViewById(R.id.rect00);
        var00.setOnClickListener{
            Toast.makeText(this, "Tocat el: "+var00.id, Toast.LENGTH_LONG).show()
        }*/
    }

    private fun startTimeCounter() {
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                countTime.text = "S'ha acabat el temps"
            }
        }.start()
    }
}