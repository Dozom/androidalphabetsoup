package com.mont.alphabetsoup

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class LevelOneActivity : AppCompatActivity() {
    lateinit var countTime: TextView
    private lateinit var letterPanel: GridLayout
    private lateinit var board: BoardController
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_one)

        board = BoardController()
        letterPanel = findViewById<GridLayout>(R.id.letterPanel)
        countTime = findViewById<TextView>(R.id.timerText)
        board.initBoard()
        for (i in 0 until 8) {
            for (k in 0 until 6) {
                val t = TextView(this)
                t.textSize = 18F
                t.width = 160
                t.height = 116
                t.text = board.boardArr[i][k].toString()
                t.gravity = Gravity.CENTER_HORIZONTAL
                t.setTypeface(null, Typeface.BOLD)
                t.setOnClickListener {
                    board.addToClicked("" + i + "" + k + "")
                    if (board.touchedCounter == 2){
                        Toast.makeText(this, board.returnCorrect().toString(), Toast.LENGTH_SHORT).show()
                        if (board.returnCorrect()){
                            repaint(board)
                        }
                        board.touchedCounter = 0
                        board.clicked = ""
                    }

                }
                letterPanel.addView(t)
            }
        }
        startTimeCounter()
    }
    private fun repaint(board: BoardController){
        Toast.makeText(this, board.returnCorrect().toString()+"re", Toast.LENGTH_SHORT).show()
        letterPanel.removeAllViews()
        for (i in 0 until 8) {
            for (k in 0 until 6) {
                val t = TextView(this)
                t.textSize = 18F
                t.width = 160
                t.height = 116
                t.gravity = Gravity.CENTER_HORIZONTAL
                t.setTypeface(null, Typeface.BOLD)

                if (board.greenSoup[i][k] == '*') {
                    t.text = board.boardArr[i][k].toString()
                    t.setBackgroundColor(Color.GREEN)
                } else {
                    t.text = board.boardArr[i][k].toString()
                    t.setBackgroundColor(Color.parseColor("#F1F8FF"))
                }
                t.setOnClickListener {
                    board.addToClicked("" + i + "" + k + "")
                    if (board.touchedCounter == 2){
                        Toast.makeText(this, board.returnCorrect().toString(), Toast.LENGTH_SHORT).show()
                        if (board.returnCorrect()){
                            repaint(board)
                        }
                        board.touchedCounter = 0
                        board.clicked = ""
                    }
                }

                letterPanel.addView(t)
            }
        }

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
        val inflater: LayoutInflater = layoutInflater
        getSystemService(LAYOUT_INFLATER_SERVICE)
        val popupView: View = inflater.inflate(R.layout.popup_window, null)

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