package com.example.pp0202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.github.jan.supabase.SupabaseClient

//import kotlinx.android.synthetic.main.homepage.*

class homepage : AppCompatActivity() {
    private lateinit var roomsButton: Button
    private lateinit var devicesButton: Button
    private lateinit var usersButton: Button
    private lateinit var settingsButton: Button
    lateinit var supabase: SupabaseClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        roomsButton.setOnClickListener{
            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)
        }
        devicesButton.setOnClickListener{
            val intent = Intent(this, DevicesActivity::class.java)
            startActivity(intent)
        }
        usersButton.setOnClickListener{
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
        settingsButton.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


    }
//    override fun finish() {
//        super.finish()
//        overrideActivityTransition(R.anim.enter_from_right, R.anim.exit_to_left)
//    }
}
