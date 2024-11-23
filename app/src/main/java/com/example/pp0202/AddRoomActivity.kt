package com.example.pp0202

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.github.jan.supabase.SupabaseClient

class AddRoomActivity : AppCompatActivity() {
    private lateinit var roomNameEditText: EditText
    private lateinit var buttons: List<Button>
    private lateinit var saveButton: Button
    private lateinit var backButton: Button
    private val existingRooms = mutableListOf("Комната1", "Комната2")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)
        roomNameEditText = findViewById(R.id.RoomName)
        saveButton = findViewById(R.id.saveButton)
        backButton = findViewById(R.id.backButton)
        buttons = listOf(findViewById(R.id.btnButton1), findViewById(R.id.btnButton2))
        roomNameEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                s?.let{
                    if(it.isNotEmpty()){
                        val words = it.toString().split(" ")
                        val capitalized = words.joinToString(" ") {word -> word.replaceFirstChar{it.uppercase()} }
                        if(it.toString() !=capitalized){
                            roomNameEditText.setText(capitalized)
                            roomNameEditText.setSelection(capitalized.length)
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        buttons.forEach{button -> button.setOnClickListener{
            buttons.forEach{btn -> btn.setBackgroundColor(Color.LTGRAY)}
            button.setBackgroundColor(Color.BLUE)
        }}
        buttons[0].performClick()
        saveButton.setOnClickListener{
            val roomName = roomNameEditText.text.toString()
            if(roomName.isEmpty()){
                Toast.makeText(this, "Имя комнаты не может быть пустым", Toast.LENGTH_SHORT).show()
            }else if(existingRooms.contains(roomName)){
                Toast.makeText(this, "Команата '$roomName' уже существует", Toast.LENGTH_SHORT).show()
            }
            else{
                saveRoom(roomName)
                Toast.makeText(this, "Комната '$roomName' успешно сохранена", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, homepage::class.java)
                startActivity(intent)
                finish()
            }
        }
        backButton.setOnClickListener{
            finish()
        }


        }
    private fun saveRoom(roomName: String){
        val newRoom = mapOf("name" to roomName)
        val response = SupabaseClient.from("rooms").insert(newRoom).execute()
        if(response.isSuccess){
            existingRooms.add(roomName)
        }
        else{
            Log.e("SaveRoom", "Ошибка сохранения комнаты: ${response.error?.message}")
            Toast.makeText(this, "Ошибка сохранения комнаты: ${response.error?.message}", Toast.LENGTH_SHORT).show()
        }
    }
}