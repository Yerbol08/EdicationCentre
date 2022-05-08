package com.example.edicationcentre1.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edicationcentre1.R

class HomeAdapter (private val list: List<ItemsHome>, var context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lesson_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val items = list[position]
        holder.name.setText(items.name)
        holder.imageView.setImageResource(items.image)
        holder.itemView.setOnClickListener{
            val intent = Intent(context, LessonsActivity::class.java)
            intent.putExtra("name", items.name)
            intent.putExtra("image", items.image)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}