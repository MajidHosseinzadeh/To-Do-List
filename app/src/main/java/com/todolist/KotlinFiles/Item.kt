package com.example.todo

import androidx.recyclerview.widget.RecyclerView
//
//class Item : RecyclerView.Adapter<TextItemViewHolder>(){
//    var data = listOf<Item>()
//
//    override fun getItemCount() = data.size
//
//    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
//        holder.textView.text = item.sleepQuality.toString()
//    }
//}

data class ItemsViewModel(val image: Int, val text: String) {
}