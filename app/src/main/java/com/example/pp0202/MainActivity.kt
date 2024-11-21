package com.example.pp0202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.gotrue.auth

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var supabaseClient: SupabaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        loginButton.setOnClickListener{authenticateUser()}
        registerButton.setOnClickListener{goToRegistrationScreen()}
        val url = "https://wqekapeowfksbnnrpxyw.supabase.co"
        val key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndxZWthcGVvd2Zrc2JubnJweHl3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzIwOTY4OTUsImV4cCI6MjA0NzY3Mjg5NX0.45Va9lfytBnq0k2qd2LdgzlKpSFi3JAVGNrARDTYPJA"
        supabaseClient = SupabaseClient(url, key)

    }

    private fun authenticateUser(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        supabaseClient.auth.signInWithEmail(email, password)
        if(email.isEmpty()||password.isEmpty()){
            showToast("Все поля должны быть заполнены")
            return
        }
        if(!isValidEmail(email)){
            showToast("Некорректный адрес электронной почты")
            return
        }
        val intent = Intent(this, CreatePin::class.java)
        startActivity(intent)
        finish()
    }
    private fun goToRegistrationScreen(){
        val intent = Intent(this,registrationScreen::class.java)
        startActivity(intent)
    }
    private fun isValidEmail(email: String):Boolean{
        val pattern = Regex("^[a-z0-9]+@[a-z0-9]+\\.ru$")
        return pattern.matches(email)
    }
    private fun showToast(message: String)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}