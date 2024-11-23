package com.example.pp0202

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class profile : AppCompatActivity() {
    private lateinit var nameUser: EditText
    private lateinit var mailemail: EditText
    private lateinit var adddresss: EditText
    private lateinit var wordpass: EditText
    private lateinit var save: Button
    private lateinit var logout: Button
    private lateinit var backnazad: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        nameUser = findViewById(R.id.nameUser)
        mailemail = findViewById(R.id.mailemail)
        adddresss = findViewById(R.id.adddresss)
        wordpass = findViewById(R.id.wordpass)
        save = findViewById(R.id.save)
        logout = findViewById(R.id.logout)
        backnazad = findViewById(R.id.backnazad)
        save.setOnClickListener{
            if(validateInputs()){
                startActivity(Intent(this, homepage::class.java))
                finish()
            }
        }
        logout.setOnClickListener{
            val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        backnazad.setOnClickListener{
            startActivity(Intent(this, homepage::class.java))
            finish()
        }
    }
    private fun validateInputs():Boolean{
        val nameUser = nameUser.text.toString().trim()
        val mailemail = mailemail.text.toString().trim()
        val adddresss = adddresss.text.toString().trim()
        val wordpass = wordpass.text.toString().trim()
        return when{
            TextUtils.isEmpty(nameUser) -> {showToast("Введите имя пользователя")
            false}
            !Patterns.EMAIL_ADDRESS.matcher(mailemail).matches() -> {showToast("Введите корректный адрес электронной почты")
                false}
            TextUtils.isEmpty(adddresss) ->{showToast("Введите адрес дома")
                false}
            wordpass.length<8 ->{showToast("Пароль должен содержать не менее 8 символов")
            false}
            else -> true
        }
    }
    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}