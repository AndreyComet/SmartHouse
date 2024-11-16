package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter

class UsersActivity : AppCompatActivity() {
    private lateinit var listViewUsers: ListView
    private lateinit var buttonAddUser: Button
    private val users = listOf(
        User(1, "Пользователь 1"),
        User(2, "Пользователь 2"),
        User(3, "Пользователь 3")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        listViewUsers = findViewById(R.id.listViewUsers)
        buttonAddUser = findViewById(R.id.buttonAddUser)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users.map{it.name})
        listViewUsers.adapter = adapter
        listViewUsers.setOnItemClickListener{_, _, position, _ -> val selectedUser = users[position]
        val intent = Intent(this, UsersActivity::class.java).apply { putExtra("USER_ID", selectedUser.id) }
            startActivity(intent)
        }
        val intent = Intent(this, AddUserActivity::class.java)
        startActivity(intent)
    }
}