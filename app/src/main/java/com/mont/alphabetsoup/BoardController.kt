package com.mont.alphabetsoup

import android.util.Log

class BoardController {
    var touchedCounter: Int = 0
    var wordStart: String = ""
    var wordEnd: String = ""
    val cat = "0002";
    //var posicions = 0002, 0002
    val fish = "1040";
    var paraules :ArrayList<String> = ArrayList<String>()
    fun addToArray(button: String){
        paraules.add(cat)
        paraules.add(fish)
        touchedCounter += 1
        if (touchedCounter == 1){
            wordStart = button;
        }
        if (touchedCounter == 2){
            wordEnd = button;
            touchedCounter = 0
        }
        //paraules = ArrayList<String>()

    }
    fun returnArray() : String{
        if (paraules.contains(wordStart+wordEnd)){
            return "Paraula correcte"
        } else{
            return "Paraula incorrecte"
        }
    }
}