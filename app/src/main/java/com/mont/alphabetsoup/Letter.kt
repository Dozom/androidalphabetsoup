package com.mont.alphabetsoup

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import java.security.AccessControlContext

class Letter : androidx.appcompat.widget.AppCompatButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        width = 1
    }
    fun setColor(){
        setBackgroundColor(Color.BLUE)
    }
    fun setColorRed(){
        setBackgroundColor(Color.RED)
    }

}