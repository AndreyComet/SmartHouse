package com.example.pp0202

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DevicesAdapter (private val devices: List<Device>,
                      private val onDeviceClick:(Device) -> Unit): RecyclerView.Adapter<DevicesAdapter.DeviceViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_device, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(devices[position])
    }

    override fun getItemCount(): Int = devices.size
    inner class DeviceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val deviceIcon: ImageView = itemView.findViewById(R.id.deviceIcon)
        private val deviceName: TextView = itemView.findViewById(R.id.deviceName)
        private val switchButton: Button = itemView.findViewById(R.id.switchButton)
        fun bind(device: Device){
            deviceIcon.setImageResource(device.iconResId)
            deviceName.text = device.name
            switchButton.text = if(device.isActive) "Выключить" else "Включить"
            switchButton.setOnClickListener{device.isActive = !device.isActive
            switchButton.text = if (device.isActive)"Выключить" else "Включить"}
            itemView.setOnClickListener{onDeviceClick(device)}
        }
    }

                      }

