package com.example.pp0202

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import io.github.jan.supabase.SupabaseClient
import io.ktor.utils.io.concurrent.shared

class AddUserActivity : AppCompatActivity() {
    private lateinit var userNameEditText: EditText
    private lateinit var email1EditText: EditText
    private lateinit var passEditText: EditText
    private lateinit var savebtn: Button
    private lateinit var deleteButton: Button
    private lateinit var backbtn: Button
    private lateinit var imageButton: ImageButton
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri? ->
        uri?.let{
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            imageButton.setImageBitmap(bitmap)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        userNameEditText = findViewById(R.id.userNameEditText)
        email1EditText = findViewById(R.id.email1EditText)
        passEditText = findViewById(R.id.passEditText)
        savebtn = findViewById(R.id.savebtn)
        deleteButton = findViewById(R.id.deleteButton)
        backbtn = findViewById(R.id.backbtn)
        imageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
                val selectedImageUri: Uri = data.data!!
                val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("user_icon", selectedImageUri.toString())
                editor.apply()
            }
        }
        Glide.with(this)
            .load("")
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageButton)
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
    private fun validateInputAndSave(){
        val userName = userNameEditText.text.toString().trim()
        val email = email1EditText.text.toString().trim()
        val password = passEditText.text.toString().trim()
        val userListQuery = SupabaseClient.from("users").select(*).execute()
        val data = mapOf("name" to userName)
        SupabaseClient.from("users").insert(data).execute()
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
