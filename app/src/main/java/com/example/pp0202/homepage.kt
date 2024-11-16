package com.example.pp0202

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import kotlinx.android.synthetic.main.homepage.*

class homepage : AppCompatActivity() {
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
