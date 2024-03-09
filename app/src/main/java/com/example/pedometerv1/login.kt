package com.example.pedometerv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.pedometerv1.databinding.ActivityHomeBinding
import com.example.pedometerv1.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()
        binding.nreg.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        binding.btnsignin.setOnClickListener {
            val email = binding.nwemail.text.toString()
            val pass = binding.ngpass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Successful logged In !", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, counter::class.java)
                        startActivity(intent)
                    } else {

                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "Something's Fishy !", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
//    If the current user is logged into the device, it will
//    stay like that untill he logs out
//    override fun onStart(){
//        super.onStart()
//
//        if(firebaseAuth.currentUser != null){
//            val intent = Intent(this, home)
//            startActivity(intent)
//        }
//    }
}
