package com.example.pp0202

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        loginButton.setOnClickListener{authenticateUser()}
        registerButton.setOnClickListener{goToRegistrationScreen()}
    }

    private fun authenticateUser(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
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