package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter


class RoomsActivity : AppCompatActivity() {
    private lateinit var listViewRooms: ListView
    private lateinit var buttonAddRoom: Button
    private val rooms = listOf(
        Room(1, "Комната 1"),
        Room(2, "Комната 2"),
        Room(3, "Комната 3")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
        listViewRooms = findViewById(R.id.listViewRooms)
        buttonAddRoom = findViewById(R.id.buttonAddRoom)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, rooms.map{it.name})
        listViewRooms.adapter = adapter
        listViewRooms.setOnItemClickListener{ _, _, position, _ -> val selectedRoom = rooms [position]
        val intent = Intent(this, RoomsActivity::class.java).apply { putExtra("ROOM_ID", selectedRoom.id) }
        startActivity(intent)
        }
        buttonAddRoom.setOnClickListener{
            val intent = Intent(this, AddRoomActivity::class.java)
            startActivity(intent)
        }
    }
}