package com.mont.alphabetsoup

import android.R.attr.button
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class LevelOne : AppCompatActivity() {
    lateinit var v00: ImageView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_one)
        var letterPanel:GridLayout = findViewById<GridLayout>(R.id.letterPanel)
        for (x in 0..5){
            for (y in 0..7){
                var l : Letter = Letter(this)
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
}