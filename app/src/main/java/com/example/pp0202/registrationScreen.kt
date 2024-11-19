package com.example.pp0202

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class registrationScreen : AppCompatActivity() {
    private lateinit var nameUserEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var regButton: Button
    private lateinit var logininButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_screen)
        nameUserEditText = findViewById(R.id.nameUserEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        regButton = findViewById(R.id.regButton)
        logininButton = findViewById(R.id.logininButton)
        regButton.setOnClickListener{
            validateInputs()
        }
        logininButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun validateInputs(){
        val username = nameUserEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if(username.isEmpty()||email.isEmpty()||password.isEmpty()){
            showError("Все поля должны быть заполнены!")
            return
        }
        if(!isEmailValid(email)){
            showError("Некорректный адрес электронной почты")
            return
        }
        if(password.length<8||!password.all{it.isDigit()}){
            showError("Пароль должен содержать не менее 8 цифр")
            return
        }
        startActivity(Intent(this,CreatePin::class.java))
    }
    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-z0-9]+@[a-z0-9]+\\.ru"
        return
        Regex(emailPattern).matches(email)
    }
    private fun showError(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}