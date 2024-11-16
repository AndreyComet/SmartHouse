package com.example.pp0202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter

class DevicesActivity : AppCompatActivity() {
    private lateinit var listViewDevices: ListView
    private lateinit var buttonAddDevice: Button
    private val devices = listOf(
        Device(1, "Устройство 1"),
        Device(2, "Устройство 2"),
        Device(3, "Устройство 3")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)
        listViewDevices = findViewById(R.id.listViewDevices)
        buttonAddDevice = findViewById(R.id.buttonAddDevice)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, devices.map{it.name})
        listViewDevices.adapter = adapter
        listViewDevices.setOnItemClickListener{_, _, position, _ -> val selectedDevice = devices[position]
        val intent = Intent(this, DevicesActivity::class.java).apply { putExtra("Device_ID", selectedDevice.id) }
        startActivity(intent)}
        buttonAddDevice.setOnClickListener{
            val intent = Intent(this, AddDeviceActivity::class.java)
            startActivity(intent)
        }
    }
}