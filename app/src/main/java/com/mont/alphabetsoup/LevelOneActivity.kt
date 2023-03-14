package com.mont.alphabetsoup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LevelOneActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    val b: BoardController = BoardController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_one)

        val tableRows = findViewById<View>(R.id.board) as TableLayout

        for (y in 0 until tableRows.childCount) {
            val row: TableRow = tableRows.getChildAt(y) as TableRow

            for (x in 0 until row.childCount) {
                val cell: TextView = findViewById(row.getChildAt(x).id)
                cell.setOnClickListener(){
                    b.cellStatusChange(y, x)
                }
            }
        }
    }
}