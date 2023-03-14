package com.mont.alphabetsoup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {
    lateinit var emailView :EditText
    lateinit var passwordView :EditText
    lateinit var usernameView : EditText
    lateinit var dateView : TextView
    lateinit var registerView : Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        emailView =findViewById<EditText>(R.id.correoEt)
        passwordView =findViewById<EditText>(R.id.passEt)
        usernameView =findViewById<EditText>(R.id.nombreEt)
        dateView =findViewById<TextView>(R.id.fechaTxt)
        registerView =findViewById<Button>(R.id.Register)

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateInstance() //or use
        getDateInstance()
        val formatedDate = formatter.format(date)
        //ara la mostrem al TextView
        this.dateView.setText(formatedDate)
        //Instanciem el firebaseAuth
        auth = FirebaseAuth.getInstance()

        registerView.setOnClickListener(){
            var email:String = emailView.getText().toString()
            var pass:String = passwordView.getText().toString()

            // validació del correu
            // si no es de tipus correu
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                this.emailView.setError("Invalid Mail")
            }
            else if (pass.length<6) {
                passwordView.setError("Password less than 6 chars")
            }
            else
            {
                signUpUser(email, pass)
            }
        }
    }
    fun signUpUser(email:String, passw:String){
        auth.createUserWithEmailAndPassword(email, passw)
            .addOnCompleteListener(this) { task -> if (task.isSuccessful) {
                // SignUp Ok
                Toast.makeText(
                    this,"createUserWithEmail:success",Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                updateUI(user)
            } else {
                // SignUp fail
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
            }
    }
    fun updateUI(user: FirebaseUser?){
//hi ha un interrogant perquè podria ser null
        if (user!=null)
        {
            var puntuacio: Int = 0
            var uidString: String = user.uid
            var correoString: String = emailView.getText().toString()
            var passString: String = passwordView.getText().toString()
            var nombreString: String = usernameView.getText().toString()
            var fechaString: String= dateView.getText().toString()
            var nivell: String = "1"

            var dadesJugador : HashMap<String,Any> = HashMap<String, Any>()
            dadesJugador.put ("Uid",uidString)
            dadesJugador.put ("Email",correoString)
            dadesJugador.put ("Password",passString)
            dadesJugador.put ("Nom",nombreString)
            dadesJugador.put ("Data",fechaString)
            dadesJugador.put ("Puntuacio",puntuacio)
            dadesJugador.put ("Nivell",nivell)

            // Creem un punter a la base de dades i li donem un nom
            var database: FirebaseDatabase = FirebaseDatabase.getInstance()
            var reference: DatabaseReference = database.getReference("jugador")
            if(reference!=null) {
                //crea un fill amb els valors de dadesJugador
                reference.child(uidString).setValue(dadesJugador)
                Toast.makeText(this, "USUARI BEN REGISTRAT", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "ERROR BD", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
        else
        {
            Toast.makeText( this,"ERROR CREATE USER",Toast.LENGTH_SHORT).show()
        }
    }
}