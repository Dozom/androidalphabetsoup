package com.mont.alphabetsoup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapter.JugadorsAdapter

class PuntuacionsActivity : AppCompatActivity() {
    val jugadors = listOf<Jugador>(
        Jugador("Pepe",12,"https://www.kasandbox.org/programming-images/avatars/piceratops-tree.png"),
        Jugador("Juanito",102,"https://www.kasandbox.org/programming-images/avatars/leafers-seed.png"),
        Jugador("Jaimito",18,"https://www.kasandbox.org/programming-images/avatars/leaf-yellow.png"),
        Jugador("Jorgito",98,"https://www.kasandbox.org/programming-images/avatars/leaf-blue.png")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_puntuacions)
        initRecyclerView()
    }
    fun initRecyclerView(){
        val recyclerView=findViewById<RecyclerView>(R.id.RecyclerOne)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= JugadorsAdapter(jugadors)
    }
}