package com.example.pp0202

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter1(private val rooms: List<String>, private val onItemClick: (String) -> Unit): RecyclerView.Adapter<RoomsFragment.RoomsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return RoomsViewHolder(view)
    }
    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int){
        holder.bind(rooms[position], onItemClick)
    }
    override fun getItemCount(): Int = rooms.size
    class RoomsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val roomNameTextView: TextView = itemView.findViewById(android.R.id.text1)
        fun bind(roomName: String, onItemClick: (String) -> Unit){
            roomNameTextView.text = roomName
            itemView.setOnClickListener{
                onItemClick(roomName)
            }
        }
    }
}