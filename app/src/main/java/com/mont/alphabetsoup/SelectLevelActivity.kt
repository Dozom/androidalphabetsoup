package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

class SelectLevelActivity : AppCompatActivity() {
    private var NOM: String =""
    private var PUNTUACIO: String=""
    private var UID: String=""
    private var NIVELL: String=""
    lateinit var imageButton1 :ImageButton
    lateinit var imageButton2 : ImageButton
    lateinit var imageButton3 :ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccioni_nivell)
        Toast.makeText(this, "WOOOOOOO", Toast.LENGTH_LONG).show()
        // Recuperem els valors que li hem passat a la vista
        var intent: Bundle? = intent.extras
        UID = intent?.getString("UID").toString()
        NOM = intent?.getString("NOM").toString()
        PUNTUACIO = intent?.getString("PUNTUACIO").toString()
        NIVELL = intent?.getString("NIVELL").toString()

        // Busco els botons de la vista
        imageButton1 = findViewById(R.id.lvl1Button)
        imageButton2 = findViewById(R.id.lvl2Button)
        imageButton3 = findViewById(R.id.lvl3Button)

        // Deshabilito els botons
        imageButton1.isEnabled = false
        imageButton2.isEnabled = false
        imageButton3.isEnabled = false
        imageButton1.visibility = View.GONE
        imageButton2.visibility = View.GONE
        imageButton3.visibility = View.GONE

        if (NIVELL == "1") {
            // En cas de que el usuari està al nivell 1
            // Fruits
            val words = arrayOf(
                "apple",
                "banana",
                "cherry",
                "date",
                "elderberry",
                "fig",
                "grape",
                "hoeneydew",
                "indian gooseberry",
                "jackfruit"
            )
            canviarnivell1()
            Toast.makeText(this, "NIVELL 1", Toast.LENGTH_LONG).show()
            imageButton1.isEnabled = true
            imageButton1.visibility = View.VISIBLE
        }
        if (NIVELL == "2") {
            // Music
            Toast.makeText(this, "NIVELL 2", Toast.LENGTH_LONG).show()
            imageButton2.isEnabled = true
            imageButton2.visibility = View.VISIBLE
        }
        if (NIVELL == "3") {
            // Animal
            Toast.makeText(this, "NIVELL 3", Toast.LENGTH_LONG).show()
            imageButton3.isEnabled = true
            imageButton3.visibility = View.VISIBLE
        }
    }

    private fun canviarnivell1() {
        val intent = Intent(this, LevelOneActivity::class.java)
        startActivity(intent)
        finish()
    }
}