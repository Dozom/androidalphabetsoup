package com.example.alphabetsoup

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.*

class Registro : AppCompatActivity() {
    lateinit var correoEt :EditText
    lateinit var passEt :EditText
    lateinit var nombreEt : EditText
    lateinit var fechaTxt : TextView
    lateinit var Registrar : Button
    lateinit var auth: FirebaseAuth //FIREBASE AUTENTIFICACIO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        correoEt =findViewById<EditText>(R.id.correoEt)
        passEt =findViewById<EditText>(R.id.passEt)
        nombreEt =findViewById<EditText>(R.id.nombreEt)
        fechaTxt =findViewById<TextView>(R.id.fechaTxt)
        Registrar =findViewById<Button>(R.id.Registrar)
        //carreguem la data al TextView
        //Utilitzem calendar (hi ha moltes altres opcions)
        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use
        getDateInstance()
        val formatedDate = formatter.format(date)
        //ara la mostrem al TextView
        fechaTxt.setText(formatedDate)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_registro)
        //Abans de fer el registre validem les dades
        33
        var email:String = correoEt.getText().toString()
        var pass:String = passEt.getText().toString()
        Registrar.setOnClickListener(){
    //Abans de fer el registre validem les dades
    // validació del correu
    // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoEt.setError("Invalid Mail")
            }
            else if (pass.length<6) {
                passEt.setError("Password less than 6 chars")
            }
            else
            {
                RegistrarJugador(email, pass)
            }
        }
    }
    fun RegistrarJugador(email:String, passw:String){
        auth.createUserWithEmailAndPassword(email, passw)
            .addOnCompleteListener(this) { task -> if (task.isSuccessful) {
// Sign in success, update UI with the signed-in user's information
                Toast.makeText(
                    this,"createUserWithEmail:success",Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                updateUI(user)
            } else {
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
//updateUI(null)
            }
            }
    }
    fun updateUI(user: FirebaseUser?){
//hi ha un interrogant perquè podria ser null
        if (user!=null)
        {
            var puntuacio: Int = 0
            var uidString: String = user.uid
            var correoString: String = correoEt.getText().toString()
            var passString: String = passEt.getText().toString()
            var nombreString: String = nombreEt.getText().toString()
            var fechaString: String= fechaTxt.getText().toString()
//AQUI GUARDA EL CONTINGUT A LA BASE DE DADES
// FALTA FER
        }
        else
        {
            Toast.makeText( this,"ERROR CREATE USER",Toast.LENGTH_SHORT).show()
        }
    }
}