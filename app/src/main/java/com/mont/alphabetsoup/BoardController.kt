package com.mont.alphabetsoup

import android.util.Log

class BoardController {
    var touchedCounter: Int = 0
    val wordStart = arrayOf(Pair(0,0))
    val wordEnd = arrayOf(Pair(0,2))
    fun cellStatusChange(y: Int, x: Int){
        val existsCellStart = wordStart.find { it.first == y && it.second == x }
        val existsCellEnd = wordEnd.find { it.first == y && it.second == x }
        if (touchedCounter == 0 && existsCellStart != null){
            touchedCounter = touchedCounter.inc()
        }
        if (touchedCounter == 1 && existsCellEnd != null){
            Log.i("Pepe", "Lo encontró")
            touchedCounter = 0
        }
    }
}