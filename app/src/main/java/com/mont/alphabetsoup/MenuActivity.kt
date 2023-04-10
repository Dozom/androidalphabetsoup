package com.mont.alphabetsoup

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class MenuActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var tancarSessio: Button
    lateinit var CreditsBtn: Button
    lateinit var PuntuacionsBtn: Button
    lateinit var jugarBtn: Button
    lateinit var canviarContrasenyaBtn: Button
    lateinit var miPuntuaciotxt: TextView
    lateinit var puntuacio: TextView
    lateinit var uid: TextView
    lateinit var correo: TextView
    lateinit var nom: TextView
    private var nivell ="1"
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_menu)
        val photo = findViewById<ImageView>(R.id.alienimagen)
        Picasso.get().load("https://d320djwtwnl5uo.cloudfront.net/recetas/cover/pechu_h6fGsnlS8gIM3iBoPC9mZQad0bXOt5.png").into(photo)
        val tf = Typeface.createFromAsset(assets,"fonts/TiltWarp-Regular.ttf")
        tancarSessio = findViewById<Button>(R.id.tancarSessio)

        // Vinculem els elements de la vista a les variables
        miPuntuaciotxt=findViewById(R.id.miPuntuaciotxt)
        puntuacio=findViewById(R.id.puntuacio)
        uid=findViewById(R.id.uid)
        correo=findViewById(R.id.correo)
        nom=findViewById(R.id.nom)
        consultaDadesJugador()

        tancarSessio =findViewById<Button>(R.id.tancarSessio)
        CreditsBtn =findViewById<Button>(R.id.CreditsBtn)
        PuntuacionsBtn =findViewById<Button>(R.id.PuntuacionsBtn)
        jugarBtn =findViewById<Button>(R.id.jugarBtn)
        canviarContrasenyaBtn = findViewById<Button>(R.id.canviarPassword)

        setFontsToElements(tf)

        // Events al fer click
        CreditsBtn.setOnClickListener(){
            creditsScreen()
        }
        PuntuacionsBtn.setOnClickListener(){
            carregarPuntuacions()
        }
        jugarBtn.setOnClickListener(){
            SeleccionarNivell()
        }
        tancarSessio.setOnClickListener(){
            tancalaSessio()
        }
        canviarContrasenyaBtn.setOnClickListener(){
            canviarContrasenya()
        }
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
    }

    private fun setFontsToElements(tf: Typeface?) {
        miPuntuaciotxt.setTypeface(tf)
        puntuacio.setTypeface(tf)
        uid.setTypeface(tf)
        correo.setTypeface(tf)
        nom.setTypeface(tf)
        tancarSessio.setTypeface(tf)
        CreditsBtn.setTypeface(tf)
        PuntuacionsBtn.setTypeface(tf)
        jugarBtn.setTypeface(tf)
        canviarContrasenyaBtn.setTypeface(tf)
    }

    override fun onStart(){
        Usuarilogejat()
        super.onStart()
    }
    private fun consultaDadesJugador() {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val bdreference:DatabaseReference = database.getReference("jugador")

        bdreference.addValueEventListener (object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var trobat =false
                for (ds in snapshot.children) {
                    Log.i ("DEBUGY","DS key:"+ds.child("Uid").key.toString())

                    if(ds.child("Email").value.toString() == user?.email){
                        trobat=true
                        puntuacio.text = ds.child("Puntuacio").value.toString()
                        uid.text = ds.child("Uid").value.toString()
                        correo.text = ds.child("Email").value.toString()
                        nom.text = ds.child("Nom").value.toString()
                        nivell = ds.child("Nivell").value.toString()
                    }
                    if (!trobat)
                    {
                        Log.e ("ERROR","ERROR NO TROBAT MAIL")
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e ("ERROR","ERROR DATABASE CANCEL")
            }
        })
    }

    private fun SeleccionarNivell() {
        val Uids: String = uid.text.toString()
        val noms: String = nom.text.toString()
        val puntuacions: String = puntuacio.text.toString()
        val nivells: String = nivell

        // Moure a la pantalla seleccioniNivell passant els parametres del jugador
        val intent = Intent(this, SelectLevelActivity::class.java)
        intent.putExtra("UID", Uids)
        intent.putExtra("NOM", noms)
        intent.putExtra("PUNTUACIO", puntuacions)
        intent.putExtra("NIVELL", nivells)
        startActivity(intent)
        finish()
    }
    private fun carregarPuntuacions() {
        // Tancar la sesió
//        auth.signOut()

        // Canviar a la pantalla de la Main Activity
        val intent= Intent(this, PuntuacionsActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun creditsScreen() {
        // Tancar la sesió
//        auth.signOut()

        // Canviar a la pantalla de la Main Activity
        val intent= Intent(this, CreditsActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun canviarContrasenya() {
        // Tancar la sesió
//        auth.signOut()
        val Uids: String = uid.text.toString()

        // Canviar a la pantalla de la Main Activity
        val intent= Intent(this, ChangePassword::class.java)
        intent.putExtra("uid", Uids)
        startActivity(intent)
        finish()
    }

    private fun tancalaSessio() {
        // Tancar la sesió
        auth.signOut()

        // Canviar a la pantalla de la Main Activity
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun Usuarilogejat()
    {
        if (user !=null)
        {
            Toast.makeText(this,"Jugador logejat",Toast.LENGTH_SHORT).show()
        }
        else
        {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}