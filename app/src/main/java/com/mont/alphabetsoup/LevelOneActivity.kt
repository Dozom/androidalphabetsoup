package com.mont.alphabetsoup

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class LevelOneActivity : AppCompatActivity() {
    lateinit var v00: ImageView
    lateinit var countTime: TextView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_one)
        val board :BoardController = BoardController()
        val letterPanel: GridLayout = findViewById<GridLayout>(R.id.letterPanel)
        countTime = findViewById<TextView>(R.id.timerText)
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val words = arrayListOf<String>("CAT", "DOG", "BIRD", "FISH", "HORSE", "CHICKEN")
        var usedPositions : ArrayList<String> = ArrayList()
        var posicioCaracter :Int = 0
        var filled : Int = 0
        var randomStart: Int = 0
        var acc: Int = 0
        for (i in 0 until 6) {
            val num = Random.nextInt(0,words.size)
            val word = words[num]
            words.removeAt(num)
            randomStart = Random.nextInt(0, 6 - word.length)
            for (j in 0 until 8) {
                val l = TextView(this)
                l.setBackgroundColor(Color.BLUE)
                l.gravity = Gravity.CENTER_HORIZONTAL
                if (j < word.length && usedPositions.contains(""+i+""+j+"") == false) {
                    l.text = word[j].toString()
                    usedPositions.add(""+i+""+j+"")
                }else{
                    l.text = alphabet.random().toString()
                }
                /*if (j >= randomStart){
                    if (acc < word.length){
                        l.text = word[acc].toString()
                        acc += 1
                        usedPositions.add(""+i+""+j+"")
                    }
                } else {
                    l.text = alphabet.random().toString()
                }*/
                l.id = (i*j)+1
                l.textSize = 30F
                l.width = 160
                l.height = 116
                l.setOnClickListener {
                    l.setBackgroundColor(Color.RED)
                    board.addToArray(""+i+""+j+"")
                    Toast.makeText(this, board.returnArray(), Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this, ""+i+""+j+"", Toast.LENGTH_SHORT).show()
                }
                letterPanel.addView(l)
            }
            //acc = 0
        }
        startTimeCounter()
    }

    private fun startTimeCounter() {
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countTime.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                countTime.text = "S'ha acabat el temps"
                showPopupWindow()
            }
        }.start()
    }

    @SuppressLint("InflateParams")
    fun showPopupWindow() {

        // inflate the layout of the popup window
        val inflater : LayoutInflater = layoutInflater
        getSystemService(LAYOUT_INFLATER_SERVICE)
        val popupView : View = inflater.inflate(R.layout.popup_window, null)

        // create the popup window
        val width = 680
        val height = 980
        val popupWindow = PopupWindow(popupView, width, height, false)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        val view = findViewById<View>(android.R.id.content)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        popupWindow.contentView.findViewById<Button>(R.id.retryButton).setOnClickListener {
            popupWindow.dismiss();
            finish();
            startActivity(intent);
        }

        popupWindow.contentView.findViewById<Button>(R.id.quitButton).setOnClickListener {
            popupWindow.dismiss();
            finish();
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}