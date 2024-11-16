package com.example.pp0202

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class Address : AppCompatActivity() {
 private lateinit var editText : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)
        editText = findViewById(R.id.editTextText)
        val btnSave = findViewById<Button>(R.id.button)
        btnSave.setOnClickListener{
            val address = editText.text.toString()
            if(isAddressValid(address)){
                val intent = Intent(this, homepage::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Некорректный адрес", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isAddressValid(address: String): Boolean{
        val regex = Regex("""^[а-яА-ЯёЁ\s]+,\s*[а-яА-ЯёЁ\s]+,\s*д\.\s*\d+,\s*кв\.\s*\d+$""")
        return regex.matches(address)
    }
}