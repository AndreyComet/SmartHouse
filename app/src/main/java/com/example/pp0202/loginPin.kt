package com.example.pp0202

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class loginPin : AppCompatActivity() {
    private lateinit var dotContainer1: LinearLayout
    private lateinit var pinDigits: MutableList<Int>
    private lateinit var digitButtons: List<Button>
    private lateinit var exitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pin)
        dotContainer1 = findViewById(R.id.dotContainer1)
        pinDigits = mutableListOf()
        digitButtons = listOf(
            findViewById(R.id.button11),
            findViewById(R.id.button22),
            findViewById(R.id.button33),
            findViewById(R.id.button44),
            findViewById(R.id.button55),
            findViewById(R.id.button66),
            findViewById(R.id.button77),
            findViewById(R.id.button88),
            findViewById(R.id.button99),
            findViewById(R.id.button00),
        )
        digitButtons.forEach{button -> button.setOnClickListener{}
            onDigitButtonClicked(button.text.toString().toInt())}

        exitButton = findViewById(R.id.exit)
        exitButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
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
        dotContainer1.removeAllViews()
        pinDigits.forEach{
            val dot = ImageView(this)
            dot.setImageResource(R.drawable.filled_dot)
            val params = LinearLayout.LayoutParams(50, 50)
            dot.layoutParams = params
            dotContainer1.addView(dot)
        }
    }
}
