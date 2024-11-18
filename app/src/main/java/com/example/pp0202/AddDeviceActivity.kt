package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.lang.Exception

class AddDeviceActivity : AppCompatActivity() {
    private lateinit var deviceNameEditText: EditText
    private lateinit var deviceIdEditText: EditText
    private lateinit var roomSpinner: Spinner
    private lateinit var btnContainer: LinearLayout
    private lateinit var btnSave: Button
    private lateinit var btnBack: Button
    private lateinit var buttons: List<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_device)
        deviceNameEditText = findViewById(R.id.deviceNameEditText)
        deviceIdEditText = findViewById(R.id.deviceIdEditText)
        roomSpinner = findViewById(R.id.roomSpinner)
        btnContainer = findViewById(R.id.btnContainer)
        btnSave = findViewById(R.id.btnSave)
        btnBack = findViewById(R.id.btnBack)
        initButtons()
        deviceNameEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                deviceNameEditText.setText(s.toString().replaceFirstChar{
                    if(it.isLowerCase())
                        it.titlecase() else it.toString()
                })
                deviceNameEditText.setSelection(deviceNameEditText.text.length)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        btnSave.setOnClickListener{
            saveDevice()
        }
        btnBack.setOnClickListener{
            onBackPressed()
        }
    }
    private fun initButtons(){
        val rooms = listOf("Команта1", "Комната2", "Команта3")
        rooms.forEach{room -> val btn = Button(this)
        btn.text = room
        btnContainer.addView(btn)
        btn.setOnClickListener{
            buttons.forEach{
                it.setBackgroundColor(Color.LTGRAY)
            }
            btn.setBackgroundColor(Color.BLUE)
        }}
    }
    private fun saveDevice(){
        val deviceName = deviceNameEditText.text.toString()
        val deviceId = deviceIdEditText.text.toString()
        val selectedRoom = roomSpinner.selectedItem.toString()
        if(deviceName.isEmpty()|| deviceId.isEmpty() || selectedRoom.isEmpty())
        {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch {
            try{
                Supabase.addDevice(deviceName, deviceId, selectedRoom)
                Toast.makeText(this@AddDeviceActivity, "Устройство '$deviceName' успешно сохранено", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AddDeviceActivity, homepage::class.java)
                startActivity(intent)
                finish()
            }catch (e: Exception){
                Toast.makeText(this@AddDeviceActivity, "Ошибка при сохранении устройства: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}