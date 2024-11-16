package com.example.pp0202

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

private const val password = "1234"
private var currentIndex = 0
private val numberButtons = arrayOfNulls<Button>(10)
private val indicatorViews = arrayOfNulls<View>(4)
class CreatePin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pin)
        for (i in 0..9) {
            val resID = resources.getIdentifier("button$i", "id", packageName)
            numberButtons[i] = findViewById(resID)
            numberButtons[i]?.setOnClickListener(View.OnClickListener {
                if (currentIndex < 4) {
                    if (password[currentIndex] == (i + '0'.code).toChar()) {
                        indicatorViews[currentIndex]!!.setBackgroundResource(R.drawable.circlecorrect)
                        currentIndex++
                        if (currentIndex == 4) {
                        }

                    }
                }
            })
        }

        for (i in 0..3) {
            val resID = resources.getIdentifier("view$i", "id", packageName)
            indicatorViews[i] = findViewById(resID)
        }


        }
    }





