package ro.sapientia.android_9eloadas.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ro.sapientia.android_9eloadas.R
import ro.sapientia.android_9eloadas.model.Item

// Item event handling
interface OnItemClickListener{
    fun onItemClick(position: Int)
}

class DataAdapter (
           private val list: List<Item>,
           private val listener: OnItemClickListener
           ):  RecyclerView.Adapter<DataAdapter.DataViewHolder>(){

    // 1. user defined ViewHolder type
    inner class DataViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView),
            OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
        // Constructor
        init{
            itemView.setOnClickListener( this )
        }

        override fun onClick(p0: View?) {
            // Delegate event handling to ListFragment
            val position: Int = adapterPosition
            if( position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }
    }



    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        ++createCounter
//        Log.i("XXX", "onCreateViewHolder ${createCounter}")
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }

    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        ++bindCounter;
//        Log.i("XXX", "onBindViewHolder ${bindCounter}")
        val currentItem = list[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
    }

    // 4.
    override fun getItemCount(): Int {
        return list.size
    }

    companion object{
        var createCounter: Int = 0
        var bindCounter: Int = 0
    }

}