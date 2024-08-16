package com.example.notesapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var homescreenFragment: Homescreen
    private lateinit var sharedPreferencesUsers: SharedPreferences
    private lateinit var isLogged: SharedPreferences
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferencesUsers = getSharedPreferences("Users", Context.MODE_PRIVATE)
        isLogged = getSharedPreferences("loginActivity", Context.MODE_PRIVATE)
        val editor = sharedPreferencesUsers.edit()
        editor.putString("9618508849", "Sagar")
        editor.putString("6302303191", "Cherry ")
        editor.putString("9573367288", "Munny")

        editor.apply()

        if (isLoggedIn() == 0) {
            goToLogin()
        } else {
            goToHomeScreen()
        }

        onclicklisterns()

    }



    private fun isLoggedIn(): Int {
        return isLogged.getInt("is_logged", 0)
    }

    private fun goToHomeScreen() {
        homescreenFragment = Homescreen()
        supportFragmentManager.beginTransaction().replace(R.id.framelt, homescreenFragment).commit()
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.framelt, fragment).commit()
    }

    private fun onclicklisterns(){

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Homescreen())
                R.id.fav -> replaceFragment(Fav())
                else -> { }
            }
            true
        }

    }
}