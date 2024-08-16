package com.example.notesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notesapp.databinding.ActivityLoginBinding
import com.example.notesapp.databinding.FragmentHomeScreenBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var isLogged: SharedPreferences
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLogged = getSharedPreferences("loginActivity", Context.MODE_PRIVATE)

        binding.btLogin.setOnClickListener {
            updateDetails()
            goToHome()
        }
    }

    private fun updateDetails() {
        val editor = isLogged.edit()
        editor.putString("PhnNumber",binding.txtinputphone.text.toString())
        editor.putInt("is_logged", 1)
        editor.apply()
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}