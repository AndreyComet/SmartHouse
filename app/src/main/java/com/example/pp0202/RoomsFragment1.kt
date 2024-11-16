package com.example.pp0202

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pp0202.R
import kotlinx.android.synthetic.main.fragment_rooms.*

class RoomsFragment1: Fragment() {
    private val rooms = listOf("Комната1", "Комната2", "Комната3")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return
        inflater.inflate(R.layout.fragment_rooms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewRooms.layoutManager = LinearLayoutManager(context)
        recyclerViewRooms.adapter = RoomsAdapter(rooms) {
                roomName -> val action = RoomsFragmentDirections.actionRoomsFragmentToDevicesFragment(roomName)
            findNavController().navigate(action)
        }
        buttonAddRoom.setOnClickListener{
            findNavController().navigate(R.id.action_roomsFragment_to_addRoomFragment)
        }
    }
}