package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddUserActivity : AppCompatActivity() {
    private lateinit var userNameEditText: EditText
    private lateinit var email1EditText: EditText
    private lateinit var passEditText: EditText
    private lateinit var savebtn: Button
    private lateinit var deleteButton: Button
    private lateinit var backbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        userNameEditText = findViewById(R.id.userNameEditText)
        email1EditText = findViewById(R.id.email1EditText)
        passEditText = findViewById(R.id.passEditText)
        savebtn = findViewById(R.id.savebtn)
        deleteButton = findViewById(R.id.deleteButton)
        backbtn = findViewById(R.id.backbtn)

        savebtn.setOnClickListener{
            validateInputAndSave()
        }
        deleteButton.setOnClickListener{
            goToMainScreen()
        }
        backbtn.setOnClickListener{
            goToMainScreen()
        }
    }
    private fun validateInputAndSave{
        val userName = userNameEditText.text.toString().trim()
        val email = email1EditText.text.toString().trim()
        val password = passEditText.text.toString().trim()
        if(userName.isEmpty()){
            Toast.makeText(this, "Пожалуйста, введите имя пользователя", Toast.LENGTH_SHORT).show()
            return
        }
        if(email.isEmpty()||!isValidEmail(email)){
            Toast.makeText(this, "Пожалуйста введите корректную электронную почту", Toast.LENGTH_SHORT).show()
            return
        }
        if(password.isEmpty()||!isValidPassword(password)){
            Toast.makeText(this, "Пароль должен содержать не менее 8 символов", Toast.LENGTH_SHORT).show()
            return
        }
     Toast.makeText(this, "Пользователь '$userName' успешно сохранён", Toast.LENGTH_SHORT).show()
     goToMainScreen()
    }
    private fun isValidEmail(email: String):Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password: String): Boolean{
        return password.length >= 8
    }
    private fun goToMainScreen(){
        val intent = Intent(this, homepage::class.java)
        startActivity(intent)
        finish()
    }

}
}