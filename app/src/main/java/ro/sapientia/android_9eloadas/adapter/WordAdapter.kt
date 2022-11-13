package ro.sapientia.android_9eloadas.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import ro.sapientia.android_9eloadas.R


class WordAdapter (
    private val list: List<String>,
):  RecyclerView.Adapter<WordAdapter.DataViewHolder>(){

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        val textView1: TextView = itemView.findViewById(R.id.textView3)
    }


    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.word_item_layout, parent, false)
        return DataViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView1.text = currentItem.toString()
    }

    // 4.
    override fun getItemCount(): Int {
        return list.size
    }

}