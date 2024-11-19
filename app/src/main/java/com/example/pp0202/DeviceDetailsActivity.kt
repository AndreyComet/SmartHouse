package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.ToggleButton

class DeviceDetailsActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var toggleButton: Button
    private lateinit var slider: SeekBar
    private lateinit var sliderValueTextView: TextView
    private lateinit var editText1: EditText
    private lateinit var selectionField: TextView
    private lateinit var dateField: TextView
    private var isDeviceActive: Boolean = false
    private var currentValue: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_details)
        backButton = findViewById(R.id.nazadback)
        toggleButton = findViewById(R.id.toggleButton)
        slider = findViewById(R.id.slider)
        sliderValueTextView = findViewById(R.id.sliderValueTextView)
        editText1 = findViewById(R.id.editText1)
        selectionField = findViewById(R.id.selectionField)
        dateField = findViewById(R.id.dateField)
        updateSliderValueText()
        backButton.setOnClickListener{
            onBackPress()
        }
        toggleButton.setOnClickListener{
            isDeviceActive = !isDeviceActive
            updateToggleButtonText()
        }
        slider.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                currentValue = progress
                updateSliderValueText()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar){}
            override fun onStopTrackingTouch(seekBar: SeekBar){}
        })
        editText1.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                validateInput(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        selectionField.setOnClickListener{showOptionDialog()}
        dateField.setOnClickListener{showDateTimePicker()}
        val deviceName = intent.getStringExtra("deviceName")
        title = deviceName?: "Устройство"
        updateToggleButtonText()
    }
    private fun updateSliderValueText(){
        sliderValueTextView.text = "Текущее значение: $currentValue"
    }
    private fun updateToggleButtonText(){
        toggleButton.text = if(isDeviceActive) "Включить" else "Выключить"
    }
    private fun validateInput(input: String){
        if(input.isNotEmpty()){
            val value = input.toIntOrNull()
            if(value == null || value < 0 || value > 100){
                editText1.error = "Введите значение от 0 до 100"
            }
            else{editText1.error = null}
        }
        else{editText1.error = "Поле не должно быть пустым"}
    }

}