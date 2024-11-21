package com.example.pp0202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class CreatePin : AppCompatActivity() {
    private lateinit var dotContainer: LinearLayout
    private lateinit var pinDigits: MutableList<Int>
    private lateinit var digitButtons: List<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)
        dotContainer = findViewById(R.id.dotContainer)
        pinDigits = mutableListOf()
        digitButtons = listOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )
        digitButtons.forEach{button -> button.setOnClickListener{}
        onDigitButtonClicked(button.text.toString().toInt())}

        }
    private fun onDigitButtonClicked(digit:Int){
        if(pinDigits.size<4){
            pinDigits.add(digit)
            updateDots()
            if(pinDigits.size == 4){
                startActivity(Intent(this, Address::class.java))
            }
        }
    }
    private fun updateDots(){
        dotContainer.removeAllViews()
        pinDigits.forEach{
            val dot = ImageView(this)
            dot.setImageResource(R.drawable.filled_dot)
            val params = LinearLayout.LayoutParams(50, 50)
            dot.layoutParams = params
            dotContainer.addView(dot)
        }
    }
}







