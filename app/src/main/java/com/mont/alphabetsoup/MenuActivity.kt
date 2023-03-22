package com.mont.alphabetsoup

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MenuActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null
    lateinit var tancarSessio: Button
    lateinit var CreditsBtn: Button
    lateinit var PuntuacionsBtn: Button
    lateinit var jugarBtn: Button
    lateinit var miPuntuaciotxt: TextView
    lateinit var puntuacio: TextView
    lateinit var uid: TextView
    lateinit var correo: TextView
    lateinit var nom: TextView
    private var nivell ="1"

    //reference serà el punter que ens envia a la base de dades de dades
    lateinit var reference: DatabaseReference

    override fun onStart(){
        Usuarilogejat()
        super.onStart()
    }
    private fun consultaDadesJugador() {
        // consulta a la base de dades de jugador
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var bdreference:DatabaseReference = database.getReference("jugador")

        bdreference.addValueEventListener (object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                Log.i ("DEBUG","arrel value"+ snapshot.getValue().toString())
//                Log.i ("DEBUG","arrel key"+ snapshot.key.toString())

                // ara capturem tots els fills
                var trobat:Boolean =false
                for (ds in snapshot.getChildren()) {
   //                 Log.i ("DEBUG","DS key:"+ds.child("Uid").key.toString())
   //                 Log.i ("DEBUG","DS value:"+ds.child("Uid").getValue().toString())
   //                 Log.i ("DEBUG","DS data:"+ds.child("Data").getValue().toString())
   //                 Log.i ("DEBUG","DS mail:"+ds.child("Email").getValue().toString())

                    // Si el email de la base de dades es igual al email que s'ha introduit
                    if(ds.child("Email").getValue().toString().equals(user?.email)){
                        trobat=true
                        // Setejar dades del jugador
                        puntuacio.setText(ds.child("Puntuacio").getValue().toString())
                        uid.setText(ds.child("Uid").getValue().toString())
                        correo.setText(ds.child("Email").getValue().toString())
                        nom.setText(ds.child("Nom").getValue().toString())
                        nivell = ds.child("Nivell").getValue().toString()
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        consultaDadesJugador()

        val tf = Typeface.createFromAsset(assets,"fonts/TiltWarp-Regular.ttf")
        tancarSessio = findViewById<Button>(R.id.tancarSessio)

        // Vinculem els elements de la vista a les variables
        miPuntuaciotxt=findViewById(R.id.miPuntuaciotxt)
        puntuacio=findViewById(R.id.puntuacio)
        uid=findViewById(R.id.uid)
        correo=findViewById(R.id.correo)
        nom=findViewById(R.id.nom)
        tancarSessio =findViewById<Button>(R.id.tancarSessio)
        CreditsBtn =findViewById<Button>(R.id.CreditsBtn)
        PuntuacionsBtn =findViewById<Button>(R.id.PuntuacionsBtn)
        jugarBtn =findViewById<Button>(R.id.jugarBtn)

        // setejar els tipus de lletra als botons o texts
        miPuntuaciotxt.setTypeface(tf)
        puntuacio.setTypeface(tf)
        uid.setTypeface(tf)
        correo.setTypeface(tf)
        nom.setTypeface(tf)
        tancarSessio.setTypeface(tf)
        CreditsBtn.setTypeface(tf)
        PuntuacionsBtn.setTypeface(tf)
        jugarBtn.setTypeface(tf)


        // Events al fer click
        CreditsBtn.setOnClickListener(){
            Toast.makeText(this,"Credits", Toast.LENGTH_SHORT).show()
        }
        PuntuacionsBtn.setOnClickListener(){
            carregarPuntuacions()
//            Toast.makeText(this,"Puntuacions", Toast.LENGTH_SHORT).show()
        }
        jugarBtn.setOnClickListener(){
            SeleccionarNivell()
        }
        tancarSessio.setOnClickListener(){
            tancalaSessio()
        }

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
    }

    private fun SeleccionarNivell() {
        var Uids: String = uid.getText().toString()
        var noms: String = nom.getText().toString()
        var puntuacions: String = puntuacio.getText().toString()
        var nivells: String = nivell

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