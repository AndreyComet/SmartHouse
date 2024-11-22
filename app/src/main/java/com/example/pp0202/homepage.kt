package com.example.pp0202

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

//import kotlinx.android.synthetic.main.homepage.*

class homepage : AppCompatActivity() {
    private lateinit var roomsButton: Button
    private lateinit var devicesButton: Button
    private lateinit var usersButton: Button
    private lateinit var settingsButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        lifecycleScope.launch {
            val city = supabase.from("cities").select().decodeSingle<dsa>()
            Log.e("!!!", city.toString())
            supabase.auth.signInWith(Email)
            {
                email = "example@email.com"
                password = "example-password"
            }
        }



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

    val supabase = createSupabaseClient(
        supabaseUrl = "https://wqekapeowfksbnnrpxyw.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndxZWthcGVvd2Zrc2JubnJweHl3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzIwOTY4OTUsImV4cCI6MjA0NzY3Mjg5NX0.45Va9lfytBnq0k2qd2LdgzlKpSFi3JAVGNrARDTYPJA"
    ) {
        install(Auth)
        install(Postgrest)
    }
    @Serializable
    data class dsa(
        val id: Int
    )



//    override fun finish() {
//        super.finish()
//        overrideActivityTransition(R.anim.enter_from_right, R.anim.exit_to_left)
//    }
}

