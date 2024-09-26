package com.upang.lms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.upang.lms.databinding.ActivityRegisterPageBinding

class RegisterPage : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.TvLogin.setOnClickListener {
            val intent = Intent (this, LoginPage::class.java )
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            val email = binding.EtEmail.text.toString()
            val pass =  binding.EtPassword.text.toString()
            val confirmPass =  binding.EtPassword2.text.toString()
            Log.d("button Testing","Working")
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                  firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful)  {
                        val intent = Intent(this, LoginPage::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this , it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                  }
                } else {
                    Toast.makeText(this , "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this , "Empty Fields is not Allowed", Toast.LENGTH_SHORT).show()
            }
        }

        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}