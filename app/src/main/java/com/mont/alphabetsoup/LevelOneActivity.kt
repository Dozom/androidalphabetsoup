package com.mont.alphabetsoup

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LevelOneActivity : AppCompatActivity() {
    lateinit var v00: ImageView
    lateinit var countTime: TextView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_one)

        val letterPanel: GridLayout = findViewById<GridLayout>(R.id.letterPanel)
        countTime = findViewById<TextView>(R.id.timerText)
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val words = arrayOf("CAT", "DOG", "BIRD", "FISH", "HORSE")
        val word = words.random()

        startTimeCounter()
/*
        for (i in word.indices) {
            val letter = word[i].toString()
            val textView = TextView(this)
            textView.text = letter
            textView.textSize = 20f
            textView.setTextColor(Color.BLACK)
            textView.setBackgroundColor(Color.BLUE)
            textView.gravity = Gravity.CENTER
            textView.width = 100;
            textView.height = 100;
            textView.setPadding(0)
            val params: GridLayout.LayoutParams = GridLayout.LayoutParams(
                GridLayout.spec(i / 8), GridLayout.spec(i % 8)
            )
            params.width = GridLayout.LayoutParams.WRAP_CONTENT
            params.height = GridLayout.LayoutParams.WRAP_CONTENT

            textView.layoutParams = params
            letterPanel.addView(textView)
            textView.setOnClickListener {
                textView.setBackgroundColor(Color.RED)
            }
        }
*/
        for (x in word.length until 32) {
            val l: Letter = Letter(this)
            l.setColor()
            l.width = 50;

            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT,
                GridLayout.LayoutParams.MATCH_PARENT,
                1f
            )
//          l.layoutParams = params
            l.gravity = Gravity.CENTER_HORIZONTAL
            l.text = alphabet.random().toString()
            l.id = x
            letterPanel.addView(l)
            l.setOnClickListener {
                l.setColorRed()
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