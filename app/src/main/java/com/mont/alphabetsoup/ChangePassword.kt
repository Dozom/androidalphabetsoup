package com.mont.alphabetsoup

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ChangePassword : AppCompatActivity() {
    lateinit var btnPasswordReset: Button
    lateinit var textPasswordReset: EditText
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canviar_contrasenya)

        var intent: Bundle? = intent.extras
        btnPasswordReset = findViewById<Button>(R.id.BtnresetPass)
        textPasswordReset = findViewById<EditText>(R.id.passReset)
        btnPasswordReset.setOnClickListener(){
            var uid = intent?.getString("uid")
            val auth = FirebaseAuth.getInstance()
            val currentUser  = auth.currentUser
            Toast.makeText(this, currentUser.toString(), Toast.LENGTH_SHORT).show()
            if (currentUser != null && currentUser.uid.equals(uid)){
                currentUser.updatePassword(textPasswordReset.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show()
                        backToMainMenu(uid)
                    } else {
                        Toast.makeText(this, "Password not updated", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun backToMainMenu(uid: String) {
        // Tancar la sesió
//        auth.signOut()

        // Canviar a la pantalla de la Main Activity
        val intent= Intent(this, MainActivity::class.java)
        intent.putExtra("uid", uid)
        startActivity(intent)
        finish()
    }

}