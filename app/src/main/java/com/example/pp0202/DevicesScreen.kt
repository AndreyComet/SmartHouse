package com.example.pp0202

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DevicesScreen : AppCompatActivity() {
    private lateinit var deviceList: RecyclerView
    private lateinit var addDeviceButton: Button
    private lateinit var backbackButton: Button
    private lateinit var roomTitleTextView: TextView
    private lateinit var devicesAdapter123: DevicesAdapter
    private val devices = mutableListOf<Device>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices_screen)
        deviceList = findViewById(R.id.deviceList)
        addDeviceButton = findViewById(R.id.addDeviceButton)
        backbackButton = findViewById(R.id.backbackButton)
        roomTitleTextView = findViewById(R.id.roomTitle)
        roomTitleTextView.text = "Гостиная"
        devicesAdapter123 = DevicesAdapter(devices){device -> onDeviceSelected(device)}
        deviceList.adapter = devicesAdapter123
        deviceList.layoutManager = LinearLayoutManager(this)
        addDeviceButton.setOnClickListener{
            openAddDeviceScreen()
        }
        backbackButton.setOnClickListener{
            onBackPressed()
        }
        loadDevices()
    }
    private fun loadDevices(){
        devices.add(Device("Шторы", R.drawable.schtora, false))
        devices.add(Device("Умная колонка", R.drawable.smartkolonka, false))
        devices.add(Device("Камера", R.drawable.camera, true))
        devicesAdapter123.noitfyDataSetChanged()
    }
    private fun openAddDeviceScreen(){
        startActivity(Intent(this, AddDeviceActivity::class.java))
    }
    private fun onDeviceSelected(device: Device){
        val intent = Intent(this, DeviceDetailsActivity::class.java)
        intent.putExtra("deviceName", device.name)
        startActivity(intent)
    }

}